package org.guardian.listeners.entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.painting.PaintingPlaceEvent;
import org.guardian.listeners.LoggingListener;

public class PaintingPlace extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPaintingPlace(final PaintingPlaceEvent event) {
    }
}
