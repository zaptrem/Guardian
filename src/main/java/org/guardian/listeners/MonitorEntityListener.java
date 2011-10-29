package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.guardian.Guardian;

public class MonitorEntityListener extends PlayerListener {

    private Guardian plugin;
    private PluginManager pm = Bukkit.getServer().getPluginManager();

    public MonitorEntityListener(final Guardian plugin) {
        this.plugin = plugin;
    }
}
