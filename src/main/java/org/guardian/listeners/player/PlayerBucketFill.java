package org.guardian.listeners.player;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.guardian.ActionType;
import org.guardian.entries.ItemEntry;
import org.guardian.listeners.LoggingListener;

public class PlayerBucketFill extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerBucketFill(final PlayerBucketFillEvent event) {
    	Block block = event.getBlockClicked();
    	consumer.queueEntry(new ItemEntry(ActionType.PLAYER_BUCKET_FILL, event.getPlayer().getName(), block.getLocation(), System.currentTimeMillis(), event.getPlayer().getItemInHand(), PLUGIN));
    }
}
