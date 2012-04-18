package org.guardian.listeners.block;

import java.util.LinkedList;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.guardian.ActionType;
import org.guardian.entries.BlockEntry;
import org.guardian.listeners.LoggingListener;


public class BlockPistonMovement extends LoggingListener{
	
		@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	    public void onBlockPistonExtend(BlockPistonExtendEvent event) {
		 
		 LinkedList<Block>blocks = new LinkedList<Block>(event.getBlocks());
		 Block temp = blocks.pollFirst();
		 consumer.queueEntry(new BlockEntry(ActionType.BLOCK_FROM_TO, PISTON, temp.getLocation(), System.currentTimeMillis(), temp.getTypeId(), temp.getData(),  0, (byte)0, PLUGIN));
		 for (Block block : blocks) {
			 consumer.queueEntry(new BlockEntry(ActionType.BLOCK_FROM_TO, PISTON, block.getLocation(), System.currentTimeMillis(), temp.getTypeId(), temp.getData(), block.getTypeId(), block.getData(), PLUGIN));
	     	 temp = block;
	     }
}}
