package org.guardian.listeners;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.guardian.Guardian;

public class NinjaListener implements Listener {

    private final Guardian plugin = Guardian.getInstance();

    public NinjaListener() {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent event) {
        if (plugin.getConf().ninjaMode && !event.getPlayer().hasPermission("guardian.see")) {
            Player player = event.getPlayer();
            String[] split = event.getMessage().split("\\s+");
            String command = split[0].substring(1);
            String args = split.length >= 2 ? split[1] : "";

            if ((command.equalsIgnoreCase("plugins") || command.equalsIgnoreCase("pl"))) {
                event.setCancelled(true);
                String message = "Plugins: ";
                List<String> output = new ArrayList<String>();
                for (Plugin pl : Bukkit.getServer().getPluginManager().getPlugins()) {
                    String name = pl.getDescription().getName();
                    if (!name.equalsIgnoreCase("Guardian")) {
                        output.add((pl.isEnabled() ? ChatColor.GREEN : ChatColor.RED) + name);
                    }
                }
                for (String o : output) {
                    message += o + ChatColor.WHITE + ", ";
                }
                player.sendMessage(message.substring(0, message.length() - 2));
                return;
            }

            if ((command.equalsIgnoreCase("version") || command.equalsIgnoreCase("ver"))
                    && args.equals("Guardian")) {
                event.setCancelled(true);
                player.sendMessage("This server is not running any plugin by that name.");
                player.sendMessage("Use /plugins to get a list of plugins.");
                return;
            }

            if (command.equalsIgnoreCase("guardian") || command.equalsIgnoreCase("gd")) {
                event.setCancelled(true);
                player.sendMessage("Unknown command. Type \"help\" for help.");
            }
        }
    }
}
