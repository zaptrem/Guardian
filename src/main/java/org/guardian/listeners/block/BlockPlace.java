package org.guardian.listeners.block;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;
import org.guardian.ActionType;
import org.guardian.entries.BlockEntry;
import org.guardian.listeners.LoggingListener;

public class BlockPlace extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockPlace(final BlockPlaceEvent event) {
        final Block block = event.getBlock();
        final Location loc = block.getLocation();
        final String playerName = event.getPlayer().getName();
        final BlockState before = event.getBlockReplacedState();
        if (guardian.getConf().isLogged(loc.getWorld().getName(), ActionType.BLOCK_PLACE, playerName)) {
            consumer.queueEntry(new BlockEntry(ActionType.BLOCK_PLACE, playerName, loc, System.currentTimeMillis(), before.getTypeId(), before.getData().getData(), block.getTypeId(), block.getData(), "Guardian"));
        }
    }
}
