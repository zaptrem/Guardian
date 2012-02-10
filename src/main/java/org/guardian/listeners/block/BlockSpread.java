package org.guardian.listeners.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockSpreadEvent;
import org.guardian.listeners.LoggingListener;

public class BlockSpread extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockSpread(final BlockSpreadEvent event) {
    }
}
