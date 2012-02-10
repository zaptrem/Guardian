package org.guardian.listeners.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.guardian.listeners.LoggingListener;

public class BlockPistonRetract extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockPistonRetract(final BlockPistonRetractEvent event) {
    }
}
