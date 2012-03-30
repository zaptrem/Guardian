package org.guardian.listeners.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.guardian.listeners.LoggingListener;

public class InventoryClick extends LoggingListener {
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onInventoryClick(final InventoryClickEvent event) {
	}

}
