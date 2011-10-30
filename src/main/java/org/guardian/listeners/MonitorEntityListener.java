package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.entity.EndermanPickupEvent;
import org.bukkit.event.entity.EndermanPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.painting.PaintingBreakEvent;
import org.bukkit.event.painting.PaintingPlaceEvent;
import org.bukkit.plugin.PluginManager;
import org.guardian.Guardian;

public class MonitorEntityListener extends EntityListener {

    private Guardian plugin;
    private PluginManager pm = Bukkit.getServer().getPluginManager();

    public MonitorEntityListener(final Guardian plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvent(Type.ENDERMAN_PICKUP, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.ENDERMAN_PLACE, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.ENTITY_DEATH, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.ENTITY_EXPLODE, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PAINTING_BREAK, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PAINTING_PLACE, this, Priority.Monitor, plugin);
    }
    @Override
    public void onEndermanPickup(EndermanPickupEvent event) {
    }
    @Override
    public void onEndermanPlace(EndermanPlaceEvent event) {
    }
    @Override
    public void onEntityDeath(EntityDeathEvent event) {
    }
    @Override
    public void onEntityExplode(EntityExplodeEvent event) {
    }
    @Override
    public void onPaintingBreak(PaintingBreakEvent event) {
    }
    @Override
    public void onPaintingPlace(PaintingPlaceEvent event) {
    }
}
