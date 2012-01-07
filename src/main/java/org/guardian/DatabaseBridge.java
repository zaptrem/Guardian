package org.guardian;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.List;
import org.guardian.entries.Entry;
import org.guardian.params.QueryParams;

public interface DatabaseBridge extends Closeable {

    /**
     * Returns all log matching specified parameters. Database specific implementing of getLog()
     **/
    List<Entry> getEntries(QueryParams params) throws SQLException;

    /**
     * Returns the count of matching specified parameters.
     **/
    int getCount(QueryParams params) throws SQLException;

    /**
     * Performs a single insert.
     **/
    void addEntry(Entry entry) throws SQLException;

    /**
     * Deletes all entries from database matching specified parameters.
     **/
    void removeEntries(QueryParams params) throws SQLException;

    /**
     * Deletes entries from database.
     * 
     * @param entries
     * DataEntries to be deleted. Id must be set.
     **/
    void removeEntries(List<Entry> entries) throws SQLException;

    /**
     * Sets rollbacked column.
     * 
     * @param entries
     * DataEntries to be modified. Id must be set.
     **/
    void setRollbacked(List<Entry> entries, boolean rollbacked) throws SQLException;

    /**
     * Checks whether the connection works. Can be implemented as ping.
     **/
    boolean test() throws SQLException;

    /**
     * Creates tables if necessary
     **/
    boolean checkTables() throws SQLException;

    /**
     * Checks whether the underlying data structure is capable to log
     **/
    boolean canLog(ActionType action);
    
}
