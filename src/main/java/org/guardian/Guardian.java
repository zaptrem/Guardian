package org.guardian;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.guardian.commands.GuardianCommandExecutor;
import org.guardian.config.Config;
import org.guardian.entries.Entry;
import org.guardian.listeners.ChestPlayerListener;
import org.guardian.listeners.ChestSpoutInventoryListener;
import org.guardian.listeners.ChestSpoutPlayerListener;
import org.guardian.listeners.MonitorBlockListener;
import org.guardian.listeners.MonitorEntityListener;
import org.guardian.listeners.MonitorPlayerListener;
import org.guardian.listeners.MonitorVehicleListener;
import org.guardian.listeners.NinjaPlayerListener;
import org.guardian.listeners.ToolPlayerListener;
import org.guardian.listeners.UtilPlayerListener;
import org.guardian.params.QueryParams;
import org.guardian.util.BukkitUtils;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class Guardian extends JavaPlugin {

    // Plugins
    private static Guardian guardian;
    private WorldEditPlugin worldEdit;
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
        // Activate listeners
        if (getConf().ninjaMode) {
            new NinjaPlayerListener();
        }
        new MonitorBlockListener();
        new MonitorEntityListener();
        new MonitorPlayerListener();
        new MonitorVehicleListener();
        new ToolPlayerListener();
        new UtilPlayerListener();
        // Check for Spout
        final Plugin spoutPlugin = getServer().getPluginManager().getPlugin("Spout");
        if (spoutPlugin.isEnabled()) {
            new ChestSpoutInventoryListener();
            new ChestSpoutPlayerListener();
            BukkitUtils.info("Spout " + spoutPlugin.getDescription().getVersion() + " has been found, accurate chest logging enabled");
        } else {
            new ChestPlayerListener();
            BukkitUtils.info("Spout has not been found, accurate chest logging disabled");
        }
        // Check for WorldEdit
        final Plugin wePlugin = getServer().getPluginManager().getPlugin("WorldEdit");
        if (wePlugin != null) {
            worldEdit = (WorldEditPlugin) wePlugin;
            BukkitUtils.info("WorldEdit " + getWorldEdit().getDescription().getVersion() + " has been found, selection rollbacks enabled");
        }
        // Initialise the session manager
        sessionMan = new SessionManager();
        // Lets get some bridge action going
        final File file = new File(getDataFolder() + File.separator + "bridges" + File.separator + getConf().bridgeName);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            BukkitUtils.severe("Could not find a valid bridge! Please check it is installed and present in config.yml");
            BukkitUtils.warning("Attempting to download the MySQL bridge for you");
            // TODO Actually download it
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
        consumerId = getServer().getScheduler().scheduleAsyncRepeatingTask(this, consumer, getConf().delayBetweenRuns * 20, getConf().delayBetweenRuns * 20);
        if (consumerId <= 0) {
            fatalError("Failed to start the consumer");
        }
        // It's all good!
        BukkitUtils.info("v" + getDescription().getVersion() + " enabled");
    }

    @Override
    public void onDisable() {
        // Cancel the consumer task
        getServer().getScheduler().cancelTask(consumerId);
        // Cancel anything else that happens to be working for us
        getServer().getScheduler().cancelTasks(this);
        // I bid ye good day
        BukkitUtils.info("v" + getDescription().getVersion() + " disabled");
    }

    public void fatalError(String error) {
        BukkitUtils.severe(error);
        BukkitUtils.severe("Fatal error detected! v" + getDescription().getVersion() + " disabled");
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
     * Returns all log matching specified parameters. Also intern methods should use this.
     * 
     * @param params
     * the query paramaters to use
     * @return A list of all entries
     * @throws SQLException
     * when there is a database error
     */
    public List<Entry> getLog(QueryParams params) throws SQLException {
        return database.getEntries(params);
    }

    /**
     * Performs a rollback on all log matching specified parameters. Also intern methods should use this.
     * 
     * @param params
     * the query paramaters to use
     */
    public void rollback(QueryParams params) {
        // TODO
    }

    /**
     * Redoes all changes matching parameters, basically a undo of a rollback. Internal methods should use this.
     * 
     * @param params
     * the query paramaters to use
     */
    public void rebuild(QueryParams params) {
        // TODO
    }

    /**
     * Deletes all log matching specified parameters. Also intern methods should use this.
     * 
     * @param params
     * the query paramaters to use
     * @throws SQLException
     * when there is a database error
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
}
