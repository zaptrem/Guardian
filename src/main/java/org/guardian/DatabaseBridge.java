package org.guardian;

import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;
import org.guardian.entries.Entry;

public interface DatabaseBridge extends Closeable {

    boolean init() throws SQLException;
    
    public Runnable getConsumer();

    public void queueEntry(Entry entry);

    public void writeLocalDump() throws IOException;

    public void dumpFailedEntry(Entry entry);
}
