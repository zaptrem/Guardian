package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.SignChangeEvent;
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
    public void onBlockSpread(BlockSpreadEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLeavesDecay(LeavesDecayEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onSignChange(SignChangeEvent event) {
    }
}
