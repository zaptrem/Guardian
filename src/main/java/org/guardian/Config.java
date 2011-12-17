package org.guardian;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;
import org.guardian.tools.Tool;
import org.guardian.tools.ToolBehavior;
import org.guardian.tools.ToolMode;
import org.guardian.util.BukkitUtils;

public class Config {

    public int version;
    public int consumerDelay;
    public String bridge;
    public boolean debug;
    public boolean ninja;
    public boolean werollback;
    public HashMap<World, WorldConfig> worlds;
    public List<Tool> tools;
    public Map<String, Tool> toolsByName;
    public Map<Integer, Tool> toolsByType;
    public ArrayList<String> ignoredPlayers;
    private Guardian plugin;

    public Config(final Guardian plugin) {
        this.plugin = plugin;
    }

    public void load() {
        final FileConfiguration config = plugin.getConfig();
        config.options().copyDefaults(true);
        plugin.saveConfig();

        version = config.getInt("version");

        bridge = config.getString("bridge", "Guardian-MySQL-0.1-SNAPSHOT");
        if (!bridge.endsWith(".jar")) {
            bridge += ".jar";
        }
        debug = config.getBoolean("debug", false);
        ninja = config.getBoolean("ninja", false);
        consumerDelay = config.getInt("consumerDelay");

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
    /*
     * Check whether the world is to be logged
     *
     */

    public boolean isLogged(World world) {
        return worlds.containsKey(world);
    }

    public boolean isIgnored(String p) {
        return ignoredPlayers.contains(p);
    }
}
