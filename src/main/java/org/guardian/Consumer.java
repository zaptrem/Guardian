package org.guardian;

import java.io.IOException;
import org.guardian.entries.Entry;

public interface Consumer {

    public Runnable getRunnable();

    public void queueEntry(Entry entry);

    public void writeLocalDump() throws IOException;

    public void dumpFailedEntry(Entry entry);
}
