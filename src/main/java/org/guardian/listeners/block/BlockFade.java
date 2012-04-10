package org.guardian.listeners.block;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockFadeEvent;
import org.guardian.ActionType;
import org.guardian.entries.BlockEntry;
import org.guardian.listeners.LoggingListener;

public class BlockFade extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockFade(final BlockFadeEvent event) {
        final Block block = event.getBlock();
        final Location loc = block.getLocation();
        if (guardian.getConf().isLogged(loc.getWorld().getName(), ActionType.BLOCK_FADE, ENVIRONMENT)) {
            consumer.queueEntry(new BlockEntry(ActionType.BLOCK_FADE, ENVIRONMENT, loc, System.currentTimeMillis(), block.getTypeId(), block.getData(), 0, (byte) 0, PLUGIN));
        }
    }
}
