package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.guardian.Guardian;

public class HighPlayerListener extends PlayerListener {

    private Guardian plugin;

    public HighPlayerListener(final Guardian plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT, this, Priority.High, plugin);
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
    }
}
