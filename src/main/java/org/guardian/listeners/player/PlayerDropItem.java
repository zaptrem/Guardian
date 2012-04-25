package org.guardian.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.guardian.ActionType;
import org.guardian.entries.ItemEntry;
import org.guardian.listeners.LoggingListener;

public class PlayerDropItem extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerDropItem(final PlayerDropItemEvent event) {
       consumer.queueEntry(new ItemEntry(ActionType.PLAYER_ITEM_DROP, event.getPlayer().getName(), event.getItemDrop().getLocation() , System.currentTimeMillis(), event.getItemDrop().getItemStack(), PLUGIN));
    }
}
