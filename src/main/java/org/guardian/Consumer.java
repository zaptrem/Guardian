package org.guardian;

import static org.guardian.util.BukkitUtils.severe;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.guardian.config.Config;
import org.guardian.entries.Entry;

public class Consumer implements Runnable
{
    private final Guardian guardian;
    private final Queue<Entry> queue = new LinkedBlockingQueue<Entry>();
    private final Lock lock = new ReentrantLock();

    private Consumer(Guardian guardian) {
        this.guardian = guardian;
    }

    public void queueEntry(Entry entry) {
        queue.add(entry);
    }

    @Override
    public void run() {
        if (queue.isEmpty() || !lock.tryLock())
            return;
        final DatabaseBridge database = guardian.getDatabaseBridge();
        final Config config = guardian.getConf();
        try {
            int count = 0;
            final long start = System.currentTimeMillis();
            while (!queue.isEmpty() && (System.currentTimeMillis() - start < config.maxTimerPerRun || count < config.forceToProcessAtLeast)) {
                final Entry entry = queue.poll();
                try {
                    database.addEntry(entry);
                } catch (final SQLException ex) {
                    dumpFailedEntry(entry);
                }
                count++;
            }
        } catch (final Exception ex) {
            severe("[Consumer] Exception: ", ex);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Dumps all entries in queue to a bunch of local files. To be called at reload or server stop.
     **/
    public void writeLocalDump() throws IOException {
        // TODO Fix file format
        final long time = System.currentTimeMillis();
        int counter = 0;
        new File("plugins/LogBlock/import/").mkdirs();
        PrintWriter writer = new PrintWriter(new File(guardian.getDataFolder(), "import/queue-" + time + "-0.sql"));
        while (!queue.isEmpty()) {
            final Entry entry = queue.poll();
            // TODO writer.println(entry.serialize());
            writer.println(entry);
            counter++;
            if (counter % 1000 == 0) {
                writer.close();
                writer = new PrintWriter(new File(guardian.getDataFolder(), "import/queue-" + time + "-" + counter / 1000 + ".sql"));
            }
        }
        writer.close();
    }

    /**
     * Stores failed entries for debug purposes and later maybe retry.
     **/
    @SuppressWarnings("unused")
    private void dumpFailedEntry(Entry entry) {
        // TODO
    }
}
