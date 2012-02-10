package org.guardian.listeners.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockFadeEvent;
import org.guardian.listeners.LoggingListener;

public class BlockFade extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockFade(final BlockFadeEvent event) {
    }
}
