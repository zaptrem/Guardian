package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EndermanPickupEvent;
import org.bukkit.event.entity.EndermanPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.painting.PaintingBreakEvent;
import org.bukkit.event.painting.PaintingPlaceEvent;
import org.guardian.Guardian;

public class EntityListener implements Listener {

    private final Guardian plugin = Guardian.getInstance();

    public EntityListener() {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEndermanPickup(EndermanPickupEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEndermanPlace(EndermanPlaceEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDeath(EntityDeathEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityExplode(EntityExplodeEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPaintingBreak(PaintingBreakEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPaintingPlace(PaintingPlaceEvent event) {
    }
}
