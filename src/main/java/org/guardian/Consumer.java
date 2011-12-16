package org.guardian;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.guardian.entries.DataEntry;
import org.guardian.util.BukkitUtils;

public class Consumer implements Runnable {

    private final Queue<DataEntry> queue = new LinkedBlockingQueue<DataEntry>();
    private final Lock lock = new ReentrantLock();

    public void queueDataEntry() {
    }

    @Override
    public void run() {
        if (queue.isEmpty() || !lock.tryLock()) {
            return;
        }
        try {
            // TODO
        } catch (final Exception ex) {
            BukkitUtils.severe("[Guardian Consumer] Exception: ", ex);
        } finally {
            // TODO Release connection
            lock.unlock();
        }
    }
}
