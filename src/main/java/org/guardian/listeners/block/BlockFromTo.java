package org.guardian.listeners.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockFromToEvent;
import org.guardian.ActionType;
import org.guardian.entries.BlockEntry;
import org.guardian.listeners.LoggingListener;

public class BlockFromTo extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockFromTo(final BlockFromToEvent event) {
        final Block from = event.getBlock();
        final Block to = event.getToBlock();
        final Location loc = from.getLocation();
        if (guardian.getConf().isLogged(loc.getWorld().getName(), ActionType.BLOCK_FROM_TO, ENVIRONMENT)) {
        	if (to.getType().compareTo((Material.WATER)) == 0) {
        		 consumer.queueEntry(new BlockEntry(ActionType.BLOCK_WATER_FLOW, ENVIRONMENT, loc, System.currentTimeMillis(), from.getTypeId(), from.getData(), to.getTypeId(), to.getData(), PLUGIN));	
        	} else if (to.getType().compareTo((Material.LAVA)) == 0) {
        		 consumer.queueEntry(new BlockEntry(ActionType.BLOCK_LAVA_FLOW, ENVIRONMENT, loc, System.currentTimeMillis(), from.getTypeId(), from.getData(), to.getTypeId(), to.getData(), PLUGIN));	
        	} else {
        		 consumer.queueEntry(new BlockEntry(ActionType.BLOCK_FROM_TO, ENVIRONMENT, loc, System.currentTimeMillis(), from.getTypeId(), from.getData(), to.getTypeId(), to.getData(), PLUGIN));
        	}
        }
    }
}
