package org.guardian;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.guardian.commands.GuardianCommandExecutor;
import org.guardian.entries.DataEntry;
import org.guardian.listeners.ToolBlockListener;
import org.guardian.listeners.NinjaPlayerListener;
import org.guardian.listeners.MonitorBlockListener;
import org.guardian.listeners.MonitorEntityListener;
import org.guardian.listeners.MonitorPlayerListener;
import org.guardian.listeners.MonitorVehicleListener;
import org.guardian.listeners.ToolPlayerListener;
import org.guardian.params.QueryParams;
import org.guardian.util.BukkitUtils;

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
        conf.load();
        // Initialise commands
        commandExecutor = new GuardianCommandExecutor();
        getCommand("guardian").setExecutor(commandExecutor);
        // Activate listeners
        if (getConf().ninjaMode) {
            new NinjaPlayerListener();
        }
        new ToolBlockListener();
        new ToolPlayerListener();
        new MonitorBlockListener();
        new MonitorEntityListener();
        new MonitorPlayerListener();
        new MonitorVehicleListener();
        // Check for Spout
        Plugin spoutPlugin = getServer().getPluginManager().getPlugin("Spout");
        if (spoutPlugin.isEnabled()) {
            // TODO activate listeners
            BukkitUtils.info("Spout " + spoutPlugin.getDescription().getVersion() + " has been found, accurate chest logging enabled");
        } else {
            // TODO alternative chest logging
        }
        // Check for WorldEdit
        Plugin wePlugin = getServer().getPluginManager().getPlugin("WorldEdit");
        if (wePlugin != null) {
            worldEdit = (WorldEditPlugin) wePlugin;
            BukkitUtils.info("WorldEdit " + getWorldEdit().getDescription().getVersion() + " has been found, selection rollbacks enabled");
        }
        // Initialise the session manager
        sessionMan = new SessionManager();
        // Lets get some bridge action going
        File file = new File(getDataFolder() + File.separator + "bridges" + File.separator + getConf().bridge);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            fatalError("Could not find a valid bridge! Please check it is installed and present in config.yml");
            return;
        } else {
            database = DatabaseLoader.loadBridge(file);
        }
        // Something went wrong
        if (database == null) {
            fatalError(getConf().bridge + " is not a database bridge!");
            return;
        }
        // Check that the database is working
        try {
            if (!database.test()) {
                fatalError("The database bridge failed to verify itself");
                return;
            }
        } catch (SQLException ex) {
            fatalError(ex.getMessage());
        }
        // Start the consumer
        consumerId = getServer().getScheduler().scheduleAsyncRepeatingTask(this, consumer, conf.consumerDelay * 20, conf.consumerDelay * 20);
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
     * @param params the query paramaters to use
     * @return A list of all entries
     * @throws SQLException  when there is a database error
     */
    public List<DataEntry> getLog(QueryParams params) throws SQLException {
        return database.getEntries(params);
    }

    /**
     * Performs a rollback on all log matching specified parameters. Also intern methods should use this.
     *
     * @param params the query paramaters to use
     */
    public void rollback(QueryParams params) {
        // TODO
    }

    /**
     * Redoes all changes matching parameters, basically a undo of a rollback. Internal methods should use this.
     *
     * @param params the query paramaters to use
     */
    public void rebuild(QueryParams params) {
        // TODO
    }

    /**
     * Deletes all log matching specified parameters. Also intern methods should use this.
     *
     * @param params the query paramaters to use
     * @throws SQLException when there is a database error
     */
    public void clearLog(QueryParams params) throws SQLException {
        database.removeEntries(params);
    }
}
