package org.guardian.listeners;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.guardian.ActionType;
import org.guardian.entries.BlockEntry;

public class BlockBreakLogging extends LoggingListener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent event) {
        if (!event.isCancelled()) {
            final Block block = event.getBlock();
            final Location loc = block.getLocation();
            final String playerName = event.getPlayer().getName();
            if (guardian.getConf().isLogged(loc.getWorld().getName(), ActionType.BLOCK_BREAK, playerName)) {
                event.getPlayer().sendMessage("break");
                consumer.queueEntry(new BlockEntry(ActionType.BLOCK_BREAK, playerName, loc, System.currentTimeMillis(), block.getTypeId(), block.getData(), 0, (byte)0, "Guardian"));
            }
        }
    }
}