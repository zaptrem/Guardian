package org.guardian.listeners.block;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockDispenseEvent;
import org.guardian.ActionType;
import org.guardian.entries.ItemEntry;
import org.guardian.listeners.LoggingListener;

public class BlockDispense extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockDispense(final BlockDispenseEvent event) {
    	Location loc = event.getBlock().getLocation();
        consumer.queueEntry(new ItemEntry(ActionType.BLOCK_DISPENSE, DISPENSER, loc, System.currentTimeMillis(), event.getItem(), PLUGIN));
    }
}
