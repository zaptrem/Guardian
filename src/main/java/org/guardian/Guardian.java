package org.guardian;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.guardian.commands.GuardianCommandExecutor;
import org.guardian.config.Config;
import org.guardian.entries.Entry;
import org.guardian.listeners.*;
import org.guardian.listeners.block.BlockBreak;
import org.guardian.listeners.block.BlockPlace;
import org.guardian.params.QueryParams;
import org.guardian.util.BukkitUtils;
import org.guardian.util.Utils;

public class Guardian extends JavaPlugin {

    // Plugins
    private static Guardian guardian;
    private static WorldEditPlugin worldEdit;
    // Config and commands
    private Config conf;
    private GuardianCommandExecutor commandExecutor;
    // Database, consumer, sessions etc
    private SessionManager sessionMan;
    private DatabaseBridge database;
    private Consumer consumer;
    private int consumerId;

    @Override
    public void onLoad() {
        guardian = this;
    }

    @Override
    public void onEnable() {
        // Load config
        conf = new Config();
        // Initialise commands
        commandExecutor = new GuardianCommandExecutor();
        getCommand("guardian").setExecutor(commandExecutor);
        // Initialise the session manager
        sessionMan = new SessionManager();
        // Lets get some bridge action going
        final File file = new File(getDataFolder() + File.separator + getConf().bridgeName);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            BukkitUtils.severe("Could not find a valid bridge! Please check it is installed and present in config.yml");
            BukkitUtils.warning("Attempting to download the MySQL bridge for you");
            Utils.download("http://goo.gl/eEg3Q", file);
        }
        if (!file.exists()) {
            fatalError(getConf().bridgeName + " can still not be found. Aborting");
            return;
        }
        database = DatabaseLoader.loadBridge(file);
        // Something went wrong
        if (database == null) {
            fatalError(getConf().bridgeName + " is not a database bridge!");
            return;
        }
        // Check that the database is working
        try {
            if (!database.init()) {
                fatalError("The database bridge failed to verify itself");
                return;
            }
        } catch (final SQLException ex) {
            fatalError(ex.getMessage());
            return;
        }
        // Start the consumer
        consumer = database.getConsumer();
        consumerId = getServer().getScheduler().scheduleAsyncRepeatingTask(this, consumer.getRunnable(), getConf().delayBetweenRuns * 20, getConf().delayBetweenRuns * 20);
        if (consumerId <= 0) {
            fatalError("Failed to start the consumer");
            return;
        }
        // Activate listeners
        if (getConf().ninjaMode) {
            new NinjaListener();
        }
        if (conf.superWorldConfig.isLogging(ActionType.BLOCK_BREAK)) {
            new BlockBreak();
        }
        if (conf.superWorldConfig.isLogging(ActionType.BLOCK_PLACE)) {
            new BlockPlace();
        }
        new EntityListener();
        new PlayerListener();
        new VehicleListener();
        new ToolListener();
        new UtilListener();
        // Check for WorldEdit
        final Plugin wePlugin = getServer().getPluginManager().getPlugin("WorldEdit");
        if (wePlugin != null) {
            worldEdit = (WorldEditPlugin) wePlugin;
            BukkitUtils.info("WorldEdit " + getWorldEdit().getDescription().getVersion() + " has been found, selection rollbacks enabled");
        }
        // It's all good!
        BukkitUtils.info("version " + getDescription().getVersion() + " enabled");
    }

    @Override
    public void onDisable() {
        // Cancel the consumer task
        getServer().getScheduler().cancelTask(consumerId);
        // Cancel anything else that happens to be working for us
        getServer().getScheduler().cancelTasks(this);
        // I bid ye good day
    }

    public void fatalError(String error) {
        BukkitUtils.severe(error);
        BukkitUtils.severe("Fatal error detected! version " + getDescription().getVersion() + " disabled");
        getServer().getPluginManager().disablePlugin(this);
    }

    /**
     * @return the current plugin instance
     */
    public static Guardian getInstance() {
        return guardian;
    }

    /**
     * @return the config
     */
    public Config getConf() {
        return conf;
    }

    /**
     * @return the current session manager
     */
    public SessionManager getSessionManager() {
        return sessionMan;
    }

    /**
     * @return the main command executor for Guardian
     */
    public GuardianCommandExecutor getCommandExecutor() {
        return commandExecutor;
    }

    /**
     * @return the WorldEdit instance
     */
    public WorldEditPlugin getWorldEdit() {
        return worldEdit;
    }

    /**
     * Returns all log matching specified parameters. Also intern methods should
     * use this.
     *
     * @param params the query paramaters to use
     * @return A list of all entries
     * @throws SQLException when there is a database error
     */
    public List<Entry> getLog(QueryParams params) throws SQLException {
        return database.getEntries(params);
    }

    /**
     * Performs a rollback on all log matching specified parameters. Also intern
     * methods should use this.
     *
     * @param params the query paramaters to use
     */
    public void rollback(QueryParams params) {
        // TODO
    }

    /**
     * Redoes all changes matching parameters, basically a undo of a rollback.
     * Internal methods should use this.
     *
     * @param params the query paramaters to use
     */
    public void rebuild(QueryParams params) {
        // TODO
    }

    /**
     * Deletes all log matching specified parameters. Also intern methods should
     * use this.
     *
     * @param params the query paramaters to use
     * @throws SQLException when there is a database error
     */
    public void clearLog(QueryParams params) throws SQLException {
        database.removeEntries(params);
    }

    /**
     * @return the currently used DatabaseBridge
     */
    public DatabaseBridge getDatabaseBridge() {
        return database;
    }

    /**
     * @return the currently used Consumer, part of the database bridge
     */
    public Consumer getConsumer() {
        return consumer;
    }
}
