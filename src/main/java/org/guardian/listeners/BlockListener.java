package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.guardian.ActionType;
import org.guardian.Consumer;
import org.guardian.Guardian;
import org.guardian.entries.BlockEntry;

public class BlockListener implements Listener {

    private final Guardian plugin = Guardian.getInstance();
    private final Consumer consumer = plugin.getConsumer();

    public BlockListener() {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent event) {
        if (!event.isCancelled()) {
            final Block block = event.getBlock();
            final Location loc = block.getLocation();
            final String playerName = event.getPlayer().getName();
            if (plugin.getConf().isLogged(loc.getWorld().getName(), ActionType.BLOCK_BREAK, playerName)) {
                event.getPlayer().sendMessage("break");
                consumer.queueEntry(new BlockEntry(ActionType.BLOCK_BREAK, playerName, loc, System.currentTimeMillis(), block.getTypeId(), block.getData(), 0, (byte) 0, "Guardian"));
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBurn(BlockBurnEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockDispense(BlockDispenseEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockFade(BlockFadeEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockForm(BlockFormEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockFromTo(BlockFromToEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockIgnite(BlockIgniteEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPhysics(BlockPhysicsEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPistonExtend(BlockPistonExtendEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPistonRetract(BlockPistonRetractEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!event.isCancelled()) {
            final Block block = event.getBlock();
            final Location loc = block.getLocation();
            final String playerName = event.getPlayer().getName();
            final BlockState before = event.getBlockReplacedState();
            if (plugin.getConf().isLogged(loc.getWorld().getName(), ActionType.BLOCK_PLACE, playerName)) {
                consumer.queueEntry(new BlockEntry(ActionType.BLOCK_PLACE, playerName, loc, System.currentTimeMillis(), before.getTypeId(), before.getData().getData(), block.getTypeId(), block.getData(), "Guardian"));
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockSpread(BlockSpreadEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLeavesDecay(LeavesDecayEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onSignChange(SignChangeEvent event) {
    }
}
