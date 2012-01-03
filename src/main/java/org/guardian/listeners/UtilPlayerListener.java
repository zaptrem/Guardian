package org.guardian.listeners;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerQuitEvent;
import org.guardian.Guardian;

public class UtilPlayerListener extends PlayerListener {

    private final Guardian plugin = Guardian.getInstance();

    public UtilPlayerListener() {
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_JOIN, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_QUIT, this, Priority.Monitor, plugin);
    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        super.onPlayerJoin(event);
    }

    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
        super.onPlayerQuit(event);
    }
}
