package org.guardian;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spout.Spout;
import org.guardian.commands.GuardianCommandExecutor;
import org.guardian.entries.DataEntry;
import org.guardian.listeners.ToolBlockListener;
import org.guardian.listeners.NinjaPlayerListener;
import org.guardian.listeners.MonitorBlockListener;
import org.guardian.listeners.MonitorEntityListener;
import org.guardian.listeners.MonitorPlayerListener;
import org.guardian.listeners.ToolPlayerListener;
import org.guardian.params.QueryParams;
import org.guardian.util.BukkitUtils;

public class Guardian extends JavaPlugin {

    // Plugins
    private static Guardian guardian;
    private WorldEditPlugin worldEdit;
    private Spout spout;
    // Config and commands
    private Config conf;
    private GuardianCommandExecutor commandExecutor;
    // Listeners
    private NinjaPlayerListener ninjaPlayerListener;
    private ToolBlockListener toolBlockListener;
    private ToolPlayerListener toolPlayerListener;
    private MonitorBlockListener monitorBlockListener;
    private MonitorEntityListener monitorEntityListener;
    private MonitorPlayerListener monitorPlayerListener;
    // Database and consumer
    private DatabaseBridge database;
    private Consumer consumer;
    private int consumerId;

    @Override
    public void onLoad() {
        guardian = this;
    }

    @Override
    public void onEnable() {
        // Error checker
        boolean errorWhileEnabling = false;
        // Load config
        conf = new Config(this);
        conf.load();
        // Initialise commands
        commandExecutor = new GuardianCommandExecutor();
        getCommand("guardian").setExecutor(commandExecutor);
        // Activate listeners
        if (getConf().ninja) {
            ninjaPlayerListener = new NinjaPlayerListener();
        }
        toolBlockListener = new ToolBlockListener();
        toolPlayerListener = new ToolPlayerListener();
        monitorBlockListener = new MonitorBlockListener();
        monitorEntityListener = new MonitorEntityListener();
        monitorPlayerListener = new MonitorPlayerListener();
        // Check for Spout
        Plugin spoutPlugin = getServer().getPluginManager().getPlugin("Spout");
        if (spoutPlugin != null) {
            spout = (Spout) spoutPlugin;
            BukkitUtils.info("Spout " + getSpout().getDescription().getVersion() + " has been found, accurate chest logging enabled");
        }
        // WorldEdit
        Plugin wePlugin = getServer().getPluginManager().getPlugin("WorldEdit");
        if (wePlugin != null) {
            worldEdit = (WorldEditPlugin) wePlugin;
            BukkitUtils.info("WorldEdit " + getWorldEdit().getDescription().getVersion() + " has been found, selection rollbacks enabled");
        }
        // Unfinished bridge stuff TODO
        File file = new File("plugins" + File.separator + "Guardian" + File.separator + "bridges" + File.separator + getConf().bridge);
        file.mkdir();
        if (!file.exists()) {
            errorWhileEnabling = true;
            BukkitUtils.severe("Could not find a valid bridge! Please check it is installed and present in config.yml");
        } else {
            database = DatabaseLoader.loadAddon(file);
        }
        // Start the consumer
        consumerId = getServer().getScheduler().scheduleAsyncRepeatingTask(this, consumer, conf.consumerDelay * 20, conf.consumerDelay * 20);
        if (consumerId <= 0) {
            errorWhileEnabling = true;
        }
        // Check for errors before giving the all clear
        if (errorWhileEnabling) {
            BukkitUtils.severe("Fatal error detected! v" + getDescription().getVersion() + " disabled");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        // It's all good!
        BukkitUtils.info("v" + getDescription().getVersion() + " enabled");
    }

    @Override
    public void onDisable() {
        // Cancel the task
        getServer().getScheduler().cancelTask(consumerId);
        // I bid ye good day
        BukkitUtils.info("v" + getDescription().getVersion() + " disabled");
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
     * @return the WorldEdit instance
     */
    public WorldEditPlugin getWorldEdit() {
        return worldEdit;
    }

    /**
     * @return the Spout instance
     */
    public Spout getSpout() {
        return spout;
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
