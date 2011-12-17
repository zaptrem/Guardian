package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.guardian.Guardian;

public class MonitorBlockListener extends BlockListener {

    private final Guardian plugin = Guardian.getInstance();

    public MonitorBlockListener() {
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_BREAK, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_BURN, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_DISPENSE, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_FADE, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_FORM, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_FROMTO, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_PHYSICS, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_PISTON_EXTEND, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_PISTON_RETRACT, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_PLACE, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_SPREAD, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.LEAVES_DECAY, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.SIGN_CHANGE, this, Priority.Monitor, plugin);
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
    }

    @Override
    public void onBlockBurn(BlockBurnEvent event) {
    }

    @Override
    public void onBlockDispense(BlockDispenseEvent event) {
    }

    @Override
    public void onBlockFade(BlockFadeEvent event) {
    }

    @Override
    public void onBlockForm(BlockFormEvent event) {
    }

    @Override
    public void onBlockFromTo(BlockFromToEvent event) {
    }

    @Override
    public void onBlockPhysics(BlockPhysicsEvent event) {
    }

    @Override
    public void onBlockPistonExtend(BlockPistonExtendEvent event) {
    }

    @Override
    public void onBlockPistonRetract(BlockPistonRetractEvent event) {
    }

    @Override
    public void onBlockPlace(BlockPlaceEvent event) {
    }

    @Override
    public void onBlockSpread(BlockSpreadEvent event) {
    }

    @Override
    public void onLeavesDecay(LeavesDecayEvent event) {
    }

    @Override
    public void onSignChange(SignChangeEvent event) {
    }
}
