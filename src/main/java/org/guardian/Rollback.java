package org.guardian;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.guardian.entries.Entry;

public class Rollback implements Runnable {

    private final Queue<Entry> entries;
    private final int taskId;

    Rollback(List<Entry> entries) {
        this.entries = new LinkedBlockingQueue<Entry>(entries);
        taskId = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Guardian.getInstance(), this, 20, 20);
    }

    @Override
    public void run() {
        int counter = 0;
        while (!entries.isEmpty() && counter < 1000) {
            final Entry entry = entries.poll();
            if (!entry.isRollbacked()) {
                for (BlockState state : entry.getRollbackBlockStates()) {
                    state.update();
                }
            }
            counter++;
        }
        Bukkit.getServer().getScheduler().cancelTask(taskId);
    }
}
