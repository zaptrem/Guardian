package org.guardian.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.guardian.ActionType;
import org.guardian.entries.ItemEntry;
import org.guardian.listeners.LoggingListener;

public class PlayerPickupItem extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerPickupItem(final PlayerPickupItemEvent event) {
        consumer.queueEntry(new ItemEntry(ActionType.PLAYER_ITEM_DROP, event.getPlayer().getName(), event.getItem().getLocation() , System.currentTimeMillis(), event.getItem().getItemStack(), PLUGIN));
    }
}
