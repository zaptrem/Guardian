package org.guardian.listeners.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockFormEvent;
import org.guardian.listeners.LoggingListener;

public class BlockForm extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockForm(final BlockFormEvent event) {
    }
}
