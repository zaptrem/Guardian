package org.guardian;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.List;
import org.guardian.entries.Entry;
import org.guardian.params.QueryParams;

public interface DatabaseBridge extends Closeable {

    Consumer getConsumer();

    /**
     * Checks whether the bridge is working. Can be implemented as ping.
     *
     * @return
     * @throws SQLException
     */
    boolean init() throws SQLException;

    /**
     * Returns all log matching specified parameters. Database specific implementing of getLog()
     *
     * @param params
     * @return
     * @throws SQLException
     */
    List<Entry> getEntries(QueryParams params) throws SQLException;

    /**
     * Returns the count of matching specified parameters.
     *
     * @param params
     * @return
     * @throws SQLException
     */
    int getCount(QueryParams params) throws SQLException;

    /**
     * Performs a single insert.
     *
     * @param entry
     * @throws SQLException
     */
    void addEntry(Entry entry) throws SQLException;

    /**
     * Deletes all entries from database matching specified parameters.
     *
     * @param params
     * @throws SQLException
     */
    void removeEntries(QueryParams params) throws SQLException;

    /**
     * Deletes entries from database.
     *
     * @param entries
     * DataEntries to be deleted. Id must be set.
     * @throws SQLException
     **/
    void removeEntries(List<Entry> entries) throws SQLException;

    /**
     * Sets rollbacked column.
     *
     * @param entries
     * DataEntries to be modified. Id must be set.
     * @param rollbacked
     * @throws SQLException
     **/
    void setRollbacked(List<Entry> entries, boolean rollbacked) throws SQLException;

    /**
     * Checks whether the underlying data structure is capable to log
     *
     * @param action
     * @return
     */
    boolean canLog(ActionType action);
}
