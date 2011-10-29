package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerListener;
import org.guardian.Guardian;

public class MonitorBlockListener extends PlayerListener {

    private Guardian plugin;

    public MonitorBlockListener(final Guardian plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_BREAK, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_BURN, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_DISPENSE, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_FADE, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_FORM, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_FROMTO, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_PHYSICS, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_PISTON_EXTEND, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_PISTON_RETRACT, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_PLACE, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_SPREAD, this, Priority.Monitor, plugin);
    }
}
