package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerListener;
import org.guardian.Guardian;

public class MonitorPlayerListener extends PlayerListener {

    private Guardian plugin;

    public MonitorPlayerListener(final Guardian plugin) {
        this.plugin = plugin;
    }
}
