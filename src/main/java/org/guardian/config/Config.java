package org.guardian.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.PermissionDefault;
import org.guardian.Guardian;
import org.guardian.params.QueryParams;
import org.guardian.tools.Tool;
import org.guardian.tools.ToolBehavior;
import org.guardian.tools.ToolMode;
import org.guardian.util.BukkitUtils;
import org.guardian.util.Utils;

public class Config {

    // Main config
    public boolean debug, checkVersion, sendStatistics, logPlayerInfo, ninjaMode;
    // Bridge config
    public String bridgeName, tablePrefix, host;
    public int port;
    public String database, username, password;
    public int maxConnections;
    public String url;
    // World config
    // TODO World config
    // Consumer config
    public int maxTimerPerRun, forceToProcessAtLeast, delayBetweenRuns;
    // Lookup config
    public int linesLimit, defaultTime, defaultDistance, linesPerPage;
    // Clearlog config
    public boolean enableAutoClearLog;
    public ArrayList<QueryParams> autoParameters = new ArrayList<QueryParams>();
    public boolean dumpClearedLog;
    // Rolback config
    public ArrayList<Integer> ignoredBlocks;
    public ArrayList<Integer> forcedBlocks;
    // TODO Permission groups
    // Tools
    public ArrayList<Tool> tools;
    public HashMap<String, Tool> toolsByName;
    public HashMap<Integer, Tool> toolsByType;
    // Materials
    // TODO material data class
    // Other
    private final Guardian plugin = Guardian.getInstance();

    public void load() {
        // Load the config
        final FileConfiguration config = plugin.getConfig();
        config.options().copyDefaults(true);
        plugin.saveConfig();
        // Populate main config
        debug = config.getBoolean("debug");
        checkVersion = config.getBoolean("checkVersion");
        sendStatistics = config.getBoolean("sendStatistics");
        logPlayerInfo = config.getBoolean("logPlayerInfo");
        ninjaMode = config.getBoolean("ninjaMode");
        // Populate bridge config
        bridgeName = config.getString("bridge.name");
        tablePrefix = config.getString("bridge.tablePrefix");
        host = config.getString("bridge.host");
        port = config.getInt("bridge.port");
        database = config.getString("bridge.database");
        username = config.getString("bridge.username");
        password = config.getString("bridge.password");
        maxConnections = config.getInt("bridge.maxConnections");
        // Populate the world config
        // TODO
        // Populate the consumer config
        maxTimerPerRun = config.getInt("consumer.maxTimePerRun");
        forceToProcessAtLeast = config.getInt("consumer.forceToProcessAtLeast");
        delayBetweenRuns = config.getInt("consumer.delayBetweenRuns");
        // Populate the lookup config
        linesLimit = config.getInt("lookup.linesLimit");
        defaultTime = Utils.parseTimeSpec(config.getString("lookup.defaultTime"));
        defaultDistance = config.getInt("lookup.defaultDistance");
        linesPerPage = config.getInt("lookup.linesPerPage");
        // Populate the clearlog config
        enableAutoClearLog = config.getBoolean("clearlog.enableAutoClearLog");
        // TODO autoParamaters
        dumpClearedLog = config.getBoolean("clearlog.dumpClearedLog");
        // Rollback config
        ignoredBlocks = (config.getIntegerList("rollback.ignoredBlocks") != null)
                ? (ArrayList<Integer>) config.getIntegerList("rollback.ignoredBlocks") : new ArrayList<Integer>();
        forcedBlocks = (config.getIntegerList("rollback.forcedBlocks") != null)
                ? (ArrayList<Integer>) config.getIntegerList("rollback.forcedBlocks") : new ArrayList<Integer>();
        // TODO permission groups

        // TODO Populate the tool config
        final Set<String> toolNames = config.getConfigurationSection("tools").getKeys(false);
        tools = new ArrayList<Tool>();
        for (final String toolName : toolNames) {
            try {
                final String path = "tools." + toolName;
                final List<String> aliases = toStringList(config.getList(path + ".aliases", null));
                final ToolBehavior leftClickBehavior = ToolBehavior.valueOf(config.getString(path + ".leftClickBehavior").toUpperCase());
                final ToolBehavior rightClickBehavior = ToolBehavior.valueOf(config.getString(path + ".rightClickBehavior").toUpperCase());
                final boolean defaultEnabled = config.getBoolean(path + ".defaultEnabled", false);
                final int item = config.getInt(path + ".item", 0);
                // TODO
                // final QueryParams params = new QueryParams();
                // params.prepareToolQuery = true;
                // params.parseArgs(Bukkit.getConsoleSender(), Arrays.asList(config.getString(path + ".params").split(" ")));
                final ToolMode mode = ToolMode.valueOf(config.getString(path + ".mode").toUpperCase());
                final PermissionDefault pdef = PermissionDefault.valueOf(config.getString(path + ".permissionDefault").toUpperCase());
                final boolean giveTool = config.getBoolean(path + ".giveTool", true);
                tools.add(new Tool(toolName, aliases, leftClickBehavior, rightClickBehavior, defaultEnabled, item, null, mode, pdef, giveTool));
            } catch (final Exception ex) {
                BukkitUtils.warning("Error at parsing tool '" + toolName + "':)", ex);
            }
        }
        toolsByName = new HashMap<String, Tool>();
        toolsByType = new HashMap<Integer, Tool>();
        for (final Tool tool : tools) {
            toolsByType.put(tool.item, tool);
            toolsByName.put(tool.name, tool);
            for (final String alias : tool.aliases) {
                toolsByName.put(alias, tool);
            }
        }
        // TODO Populate the material config
    }

    public List<String> toStringList(List<?> list) {
        if (list == null) {
            return new ArrayList<String>();
        }
        final List<String> strs = new ArrayList<String>(list.size());
        for (final Object obj : list) {
            if (obj instanceof String) {
                strs.add((String) obj);
            } else {
                strs.add(String.valueOf(obj));
            }
        }
        return strs;
    }

    public boolean isLogged(World world) {
        return worlds.containsKey(world);
    }

    public boolean isIgnored(String p) {
        return ignoredPlayers.contains(p);
    }
}
