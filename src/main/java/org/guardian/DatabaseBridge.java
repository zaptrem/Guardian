package org.guardian;

import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.guardian.entries.Entry;
import org.guardian.params.QueryParams;

public interface DatabaseBridge extends Closeable, Runnable {

    public void dumpFailedEntry(Entry entry);

    public List<Entry> getEntries(QueryParams params) throws SQLException;

    public int getQueueSize();

    boolean init() throws SQLException;

    public void queueEntry(Entry entry);

    public void writeLocalDump() throws IOException;
}
