package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.guardian.Guardian;

public class VehicleListener implements Listener{

    private final Guardian plugin = Guardian.getInstance();

    public VehicleListener() {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onVehicleCreate(VehicleCreateEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onVehicleDestroy(VehicleDestroyEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onVehicleEnter(VehicleEnterEvent event) {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onVehicleExit(VehicleExitEvent event) {
    }
}
