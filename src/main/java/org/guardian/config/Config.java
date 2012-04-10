package org.guardian.config;

import java.io.File;
import java.util.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.guardian.ActionType;
import org.guardian.Guardian;
import org.guardian.params.QueryParams;
import org.guardian.permissions.RollbackSize;
import org.guardian.permissions.RollbackSizes;
import org.guardian.tools.Tool;
import org.guardian.tools.ToolBehavior;
import org.guardian.tools.ToolMode;
import org.guardian.util.BukkitUtils;
import org.guardian.util.Utils;

public final class Config {

    // Main config
    public boolean debug, checkVersion, sendStatistics, logPlayerInfo, ninjaMode, motd;
    // Bridge config
    public String bridgeName, tablePrefix, host;
    public int port;
    public String database, username, password;
    public int maxConnections;
    public String url;
    // World config
    public HashMap<String, WorldConfig> worlds = new HashMap<String, WorldConfig>();
    public WorldConfig superWorldConfig;
    // Consumer config
    public int maxTimerPerRun, forceToProcessAtLeast, delayBetweenRuns;
    // Lookup config
    public int linesLimit, defaultTime, defaultDistance, linesPerPage;
    // Clearlog config
    public boolean enableAutoClearLog;
    public ArrayList<QueryParams> autoParameters = new ArrayList<QueryParams>();
    public boolean dumpClearedLog;
    // Rolback config
    public List<Integer> ignoredBlocks;
    public List<Integer> forcedBlocks;
    public EnumMap<RollbackSize, RollbackSizes> rollbackSizes = new EnumMap<RollbackSize, RollbackSizes>(RollbackSize.class);
    // Tools
    public ArrayList<Tool> tools;
    public HashMap<String, Tool> toolsByName;
    public HashMap<Integer, Tool> toolsByType;
    // Materials
    public MaterialData materialDataManager;
    // Other
    private final Guardian plugin = Guardian.getInstance();

    public Config() {
        load();
    }

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
        motd = config.getBoolean("motd");
        // Populate bridge config
        bridgeName = config.getString("bridge.name");
        tablePrefix = config.getString("bridge.tablePrefix");
        host = config.getString("bridge.host");
        port = config.getInt("bridge.port");
        database = config.getString("bridge.database");
        username = config.getString("bridge.username");
        password = config.getString("bridge.password");
        maxConnections = config.getInt("bridge.maxConnections");
        url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        // Populate the world config
        final ConfigurationSection globalSection = config.getConfigurationSection("worlds.global");
        for (final World world : Bukkit.getServer().getWorlds()) {
            final String name = world.getName();
            ConfigurationSection cs = config.getConfigurationSection("worlds." + world.getName());
            if (cs == null) {
                cs = globalSection;
            }
            for (final String key : cs.getKeys(false)) {
                if (key == null) {
                    cs.set(key, globalSection.get(key));
                }
            }
            worlds.put(name, new WorldConfig(cs));
        }
        superWorldConfig = new WorldConfig(worlds.values());
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
        ignoredBlocks = config.getIntegerList("rollback.ignoredBlocks") != null
                ? config.getIntegerList("rollback.ignoredBlocks") : new ArrayList<Integer>();
        forcedBlocks = config.getIntegerList("rollback.forcedBlocks") != null
                ? config.getIntegerList("rollback.forcedBlocks") : new ArrayList<Integer>();
        // Permissions
        final ConfigurationSection permSection = config.getConfigurationSection("rollback.sizes");
        for (String key : permSection.getKeys(false)) {
            key = key.toUpperCase();
            rollbackSizes.put(RollbackSize.valueOf(key), new RollbackSizes(permSection.getInt(key + ".maxArea"), permSection.getInt(key + ".maxTime")));
        }
        // TODO Populate the tool config
        final Set<String> toolNames = config.getConfigurationSection("tools").getKeys(false);
        tools = new ArrayList<Tool>();
        for (final String toolName : toolNames) {
            try {
                final String path = "tools." + toolName;
                final List<String> aliases = config.getStringList(path + ".aliases");
                final ToolBehavior leftClickBehavior = ToolBehavior.valueOf(config.getString(path + ".leftClickBehavior").toUpperCase());
                final ToolBehavior rightClickBehavior = ToolBehavior.valueOf(config.getString(path + ".rightClickBehavior").toUpperCase());
                final boolean defaultEnabled = config.getBoolean(path + ".defaultEnabled", false);
                final int item = config.getInt(path + ".item", 0);
                // TODO
                // final QueryParams params = new QueryParams();
                // params.prepareToolQuery = true;
                // params.parseArgs(Bukkit.getConsoleSender(),
                // Arrays.asList(config.getString(path +
                // ".params").split(" ")));
                final ToolMode mode = ToolMode.valueOf(config.getString(path + ".mode").toUpperCase());
                final boolean giveTool = config.getBoolean(path + ".giveTool", true);
                tools.add(new Tool(toolName, aliases, leftClickBehavior, rightClickBehavior, defaultEnabled, item, new QueryParams(), mode, giveTool));
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
        // Materials
        materialDataManager = new MaterialData(new File(plugin.getDataFolder().getPath() + File.separator + "materials.yml"));
    }

    public boolean isLogged(String world) {
        final WorldConfig wcfg = worlds.get(world);
        return wcfg != null && !wcfg.isIgnored();
    }

    public boolean isLogged(String world, ActionType action) {
        final WorldConfig wcfg = worlds.get(world);
        return wcfg != null && !wcfg.isIgnored() && wcfg.isLogging(action);
    }

    public boolean isLogging(Player p) {
        final WorldConfig wcfg = worlds.get(p.getWorld().getName());
        return wcfg != null && !wcfg.isIgnored(p.getName());
    }

    public boolean isLogged(String world, ActionType action, String playerName) {
        final WorldConfig wcfg = worlds.get(world);
        return wcfg != null && !wcfg.isIgnored() && wcfg.isLogging(action) && !wcfg.isIgnored(playerName);
    }
}
