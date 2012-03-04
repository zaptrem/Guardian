package org.guardian.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.guardian.listeners.LoggingListener;

public class PlayerBedLeave extends LoggingListener {
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerBedLeave(final PlayerBucketEmptyEvent event) {
    }
}
