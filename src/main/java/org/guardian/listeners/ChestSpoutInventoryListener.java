package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.getspout.spoutapi.event.inventory.InventoryCloseEvent;
import org.getspout.spoutapi.event.inventory.InventoryListener;
import org.getspout.spoutapi.event.inventory.InventoryOpenEvent;
import org.guardian.Guardian;

public class ChestSpoutInventoryListener extends InventoryListener {

    private final Guardian plugin = Guardian.getInstance();

    public ChestSpoutInventoryListener() {
        Bukkit.getServer().getPluginManager().registerEvent(Type.CUSTOM_EVENT, this, Priority.Monitor, plugin);
    }

    @Override
    public void onInventoryClose(InventoryCloseEvent event) {
    }

    @Override
    public void onInventoryOpen(InventoryOpenEvent event) {
    }
}
