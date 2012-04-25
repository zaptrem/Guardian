package org.guardian.listeners.block;

import java.util.LinkedList;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.guardian.ActionType;
import org.guardian.entries.BlockEntry;
import org.guardian.listeners.LoggingListener;

public class BlockPistonMovement extends LoggingListener {

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onBlockPistonExtend(BlockPistonExtendEvent event) {

		LinkedList<Block> blocks = new LinkedList<Block>(event.getBlocks());
		Block temp = blocks.pollFirst();
		if (temp != null) {
			consumer.queueEntry(new BlockEntry(ActionType.BLOCK_FROM_TO,
					PISTON, temp.getLocation(), System.currentTimeMillis(),
					temp.getTypeId(), temp.getData(), 0, (byte) 0, PLUGIN));
			for (Block block : blocks) {
				consumer.queueEntry(new BlockEntry(ActionType.BLOCK_FROM_TO,
						PISTON, block.getLocation(),
						System.currentTimeMillis(), temp.getTypeId(), temp
								.getData(), block.getTypeId(), block.getData(),
						PLUGIN));
				temp = block;
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onBlockPistonRetract(BlockPistonRetractEvent event) {
		if (event.isSticky()) {
			Block piston = event.getBlock();
			int x = event.getDirection().getModX();
			int y = event.getDirection().getModY();
			int z = event.getDirection().getModZ();
			if (x == 1) {
				x++;
			} else if (x == -1) {
				x--;
			}
			if (y == 1) {
				y++;
			} else if (y == -1) {
				y--;
			}
			if (z == 1) {
				z++;
			} else if (z == -1) {
				z--;
			}
			Block block = piston.getRelative(x, y, z);
			consumer.queueEntry(new BlockEntry(ActionType.BLOCK_FROM_TO,
					PISTON, block.getLocation(), System.currentTimeMillis(),
					block.getTypeId(), block.getData(), org.bukkit.Material.AIR
							.getId(), (byte) 0, PLUGIN));

		}
	}
}
