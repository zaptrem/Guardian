package org.guardian;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.guardian.commands.GuardianCommandExecutor;
import org.guardian.entries.DataEntry;
import org.guardian.listeners.HighBlockListener;
import org.guardian.listeners.HighPlayerListener;
import org.guardian.listeners.MonitorBlockListener;
import org.guardian.listeners.MonitorEntityListener;
import org.guardian.listeners.MonitorPlayerListener;
import org.guardian.params.QueryParams;
import org.guardian.util.BukkitUtils;

public class Guardian extends JavaPlugin {

    public WorldEditPlugin worldEdit;
    private static Guardian guardian;
    private boolean errorWhileLoading = false;
    private DatabaseBridge database;
    private Config conf;
    private GuardianCommandExecutor commandExecutor;
    private HighPlayerListener highPlayerListener;
    private MonitorBlockListener monitorBlockListener;
    private MonitorEntityListener monitorEntityListener;
    private MonitorPlayerListener monitorPlayerListener;

    @Override
    public void onLoad() {
        guardian = this;
    }

    @Override
    public void onEnable() {
        conf = new Config(this);
        conf.load();

        commandExecutor = new GuardianCommandExecutor(this);
        getCommand("guardian").setExecutor(commandExecutor);

        highPlayerListener = new HighPlayerListener(this);
        monitorBlockListener = new MonitorBlockListener(this);
        monitorEntityListener = new MonitorEntityListener(this);
        monitorPlayerListener = new MonitorPlayerListener(this);

        final PluginManager pm = getServer().getPluginManager();
        Plugin we = pm.getPlugin("WorldEdit");
        if (we != null) {
            worldEdit = (WorldEditPlugin) we;
            BukkitUtils.info("WorldEdit " + worldEdit.getDescription().getVersion() + " has been found, selection rollbacks enabled");
        }
        File dirs = new File("plugins" + File.separator + "Guardian" + File.separator + "bridges" + File.separator);
        File file = new File("plugins" + File.separator + "Guardian" + File.separator + "bridges" + File.separator + getConf().bridge);
        dirs.mkdirs();
        if (!file.exists()) {
            errorWhileLoading = true;
            BukkitUtils.severe("Could not find a valid bridge! Please check it is installed and present in config.yml");
        } else {
            setDatabase(DatabaseLoader.loadAddon(file));
        }

        if (errorWhileLoading) {
            BukkitUtils.severe("Fatal error detected! v" + getDescription().getVersion() + " disabled");
            pm.disablePlugin(this);
            return;
        }

        BukkitUtils.info("v" + getDescription().getVersion() + " enabled");
    }

    @Override
    public void onDisable() {
        BukkitUtils.info("v" + getDescription().getVersion() + " disabled");
    }

    public static Guardian getInstance() {
        return guardian;
    }

    /**
     * Sets the database. Should be called once by the database bridge plugin in onLoad() event.
     **/
    public void setDatabase(DatabaseBridge database) {
        if (this.database == null) {
            this.database = database;
        }
    }

    /**
     * Returns all log matching specified parameters. Also intern methods should use this.
     **/
    public List<DataEntry> getLog(QueryParams params) throws SQLException {
        return database.getEntries(params);
    }

    /**
     * Performs a rollback on all log matching specified parameters. Also intern methods should use this.
     **/
    public void rollback(QueryParams params) {
        // TODO
    }

    /**
     * Redoes all changes matching parameters, basically a undo of a rollback. Internal methods should use this.
     **/
    public void rebuild(QueryParams params) {
        // TODO
    }

    /**
     * Deletes all log matching specified parameters. Also intern methods should use this.
     **/
    public void clearLog(QueryParams params) throws SQLException {
        database.removeEntries(params);
    }

    /**
     * @return the config
     */
    public Config getConf() {
        return conf;
    }
}
