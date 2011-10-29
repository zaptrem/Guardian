package org.guardian;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.guardian.commands.GuardianCommandExecutor;
import org.guardian.entries.DataEntry;
import org.guardian.params.QueryParams;
import org.guardian.util.BukkitUtils;

public class Guardian extends JavaPlugin {

    public static final Logger logger = Bukkit.getLogger();
    public WorldEditPlugin worldEdit = null;
    public GuardianCommandExecutor commandExecutor;
    private static Guardian guardian;
    private boolean errorWhileLoading = false;
    private DatabaseBridge database = null;

    public static Guardian getInstance() {
        return guardian;
    }

    @Override
    public void onLoad() {
        guardian = this;
    }

    @Override
    public void onEnable() {
        final PluginManager pm = getServer().getPluginManager();

        Config.load(this);

        Plugin we = pm.getPlugin("WorldEdit");
        if (we != null) {
            worldEdit = (WorldEditPlugin) we;
            BukkitUtils.info(worldEdit.getDescription().getVersion() + " has been found, selection rollbacks enabled");
        }

        commandExecutor = new GuardianCommandExecutor(this);
        getCommand("guardian").setExecutor(commandExecutor);

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
}
