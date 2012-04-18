package org.guardian.listeners.player;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.guardian.ActionType;
import org.guardian.entries.BlockEntry;
import org.guardian.listeners.LoggingListener;

public class PlayerBucketEmpty extends LoggingListener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerBucketEmpty(final PlayerBucketEmptyEvent event) {
    	Block block = event.getBlockClicked().getRelative(event.getBlockFace());
    	int id = 0;
    	if (event.getBucket().compareTo(Material.LAVA_BUCKET) == 0) {
    		id = Material.LAVA.getId();
    	} else {
    		id = Material.WATER.getId();
    	}
     	consumer.queueEntry(new BlockEntry(ActionType.BLOCK_PLACE, event.getPlayer().getName(), block.getLocation(), System.currentTimeMillis(), 0 , (byte) 0 ,  id, block.getData(), PLUGIN));
    }
}
