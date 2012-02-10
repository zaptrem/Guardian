package org.guardian.listeners.entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.painting.PaintingBreakEvent;
import org.guardian.listeners.LoggingListener;

public class PaintingBreak extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPaintingBreak(final PaintingBreakEvent event) {
    }
}
