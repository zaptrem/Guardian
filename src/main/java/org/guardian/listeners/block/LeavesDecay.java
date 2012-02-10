package org.guardian.listeners.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.LeavesDecayEvent;
import org.guardian.listeners.LoggingListener;

public class LeavesDecay extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onLeavesDecay(final LeavesDecayEvent event) {
    }
}
