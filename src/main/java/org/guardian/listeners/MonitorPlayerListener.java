package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerListener;
import org.guardian.Guardian;

public class MonitorPlayerListener extends PlayerListener {

    private final Guardian plugin = Guardian.getInstance();

    public MonitorPlayerListener() {
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_CHAT, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_COMMAND_PREPROCESS, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_BED_ENTER, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_BED_LEAVE, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_DROP_ITEM, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_JOIN, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_KICK, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_LOGIN, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_PICKUP_ITEM, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_QUIT, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_TELEPORT, this, Priority.Monitor, plugin);
    }
}
