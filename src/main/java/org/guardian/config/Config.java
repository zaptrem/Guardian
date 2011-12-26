package org.guardian.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.PermissionDefault;
import org.guardian.Guardian;
import org.guardian.WorldConfig;
import org.guardian.tools.Tool;
import org.guardian.tools.ToolBehavior;
import org.guardian.tools.ToolMode;
import org.guardian.util.BukkitUtils;

public class Config {

    // Main config
    public boolean debug, checkVersion, sendStatistics, logPlayerInfo, ninjaMode;
    // Bridge config
    public String name, tablePrefix, host;
    public int port;
    public String database, username, password;
    public File path;
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
    public ArrayList<String> autoParameters =  new ArrayList<String>();
    public boolean dumpClearedLog;
    // Rolback config
    public ArrayList<Integer> ignoredBlocks = new ArrayList<Integer>();
    public ArrayList<Integer> forcedBlocks = new ArrayList<Integer>();
    // TODO Permission groups
    // Tools
    public ArrayList<Tool> tools;
    public HashMap<String, Tool> toolsByName;
    public HashMap<Integer, Tool> toolsByType;
    // Other
    private final Guardian plugin = Guardian.getInstance();

    public void load() {
        // Load the config
        final FileConfiguration config = plugin.getConfig();
        config.options().copyDefaults(true);
        plugin.saveConfig();
        // Populate main config
        debug = config.getBoolean("debug");
        bridge = config.getString("bridge");
        ninjaMode = config.getBoolean("ninjaMode");
        consumerDelay = config.getInt("consumerDelay");
        werollback = config.getBoolean("werollback");
        
        ignoredPlayers = new ArrayList<String>();
        for (final String worldName : toStringList(config.getStringList("loggedWorlds"))) {
            final World world = Bukkit.getServer().getWorld(worldName);
            if (world != null) {
                try {
                    worlds.put(world, new WorldConfig(new File(plugin.getDataFolder(), world.getWorldFolder().getName())));
                } catch (final IOException ex) {
                    BukkitUtils.severe("Failed to save world config", ex);
                }
            } else {
                BukkitUtils.warning("There is no world called '" + worldName + "'");
            }
        }

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
