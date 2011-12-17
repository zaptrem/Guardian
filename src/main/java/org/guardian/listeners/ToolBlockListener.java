package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.guardian.Guardian;

public class ToolBlockListener extends BlockListener {

    private final Guardian plugin = Guardian.getInstance();

    public ToolBlockListener() {
        Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_PLACE, this, Priority.Normal, plugin);
    }

    @Override
    public void onBlockPlace(BlockPlaceEvent event) {
    }
}
