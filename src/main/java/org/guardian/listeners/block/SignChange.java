package org.guardian.listeners.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.SignChangeEvent;
import org.guardian.listeners.LoggingListener;

public class SignChange extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onSignChange(final SignChangeEvent event) {
    }
}
