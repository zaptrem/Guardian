package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.guardian.Guardian;

public class ChestSpoutPlayerListener extends PlayerListener {

    private final Guardian plugin = Guardian.getInstance();

    public ChestSpoutPlayerListener() {
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT, this, Priority.Monitor, plugin);
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        super.onPlayerInteract(event);
    }
}
