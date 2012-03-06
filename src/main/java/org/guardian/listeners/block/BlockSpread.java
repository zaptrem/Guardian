package org.guardian.listeners.block;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockSpreadEvent;
import org.guardian.ActionType;
import org.guardian.entries.BlockEntry;
import org.guardian.listeners.LoggingListener;

public class BlockSpread extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockSpread(final BlockSpreadEvent event) {
        final Block block = event.getBlock();
        final Location loc = block.getLocation();

        if (guardian.getConf().isLogged(loc.getWorld().getName(), ActionType.BLOCK_SPREAD, ENVIRONMENT)) {
            consumer.queueEntry(new BlockEntry(ActionType.BLOCK_SPREAD, ENVIRONMENT, loc, System.currentTimeMillis(),
                    0, (byte) 0, block.getTypeId(), block.getData(), PLUGIN));
        }
    }
}
