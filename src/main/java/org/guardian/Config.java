package org.guardian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.PermissionDefault;
import org.guardian.tools.Tool;
import org.guardian.tools.ToolBehavior;
import org.guardian.tools.ToolMode;
import org.guardian.util.BukkitUtils;

public class Config {

    public static int version;
    public static String url, user, password;
    public static boolean debug;
    public static boolean ninja;
    public static boolean werollback;
    public static HashMap<String, Tool> toolsByName;
    public static HashMap<Integer, Tool> toolsByType;

    static void load(Guardian guardian) {
        FileConfiguration config = guardian.getConfig();
        config.options().copyDefaults(true);
        guardian.saveConfig();

        version = config.getInt("version");

        url = "jdbc:mysql://" + config.getString("mysql.host") + ":" + config.getString("mysql.port") + "/" + config.getString("mysql.database");
        user = config.getString("mysql.user");
        password = config.getString("mysql.password");

        debug = config.getBoolean("debug", false);
        ninja = config.getBoolean("ninja", false);
        werollback = config.getBoolean("werollback", true);

        final ConfigurationSection configSec = config.getConfigurationSection("tools");
        final Set<String> toolNames = configSec.getKeys(false);
        final List<Tool> tools = new ArrayList<Tool>();
        for (final String toolName : toolNames) {
            try {
                final String path = "tools." + toolName;
                final List<String> aliases = config.getList(path + ".aliases", null);
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
}
