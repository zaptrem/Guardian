package org.guardian.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerQuitEvent;
import org.guardian.entries.PlayerEntry;
import org.guardian.listeners.LoggingListener;

public class PlayerQuit extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerQuit(final PlayerQuitEvent event) {
        PlayerEntry tempEntry = new PlayerEntry();
        tempEntry.setPlayerLogout(true);
        tempEntry.setName(event.getPlayer().getName());
        tempEntry.setLeaveTime(System.currentTimeMillis() / 1000);
        consumer.queueEntry(tempEntry);
    }
}
