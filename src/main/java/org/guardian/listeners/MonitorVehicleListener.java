package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleListener;
import org.guardian.Guardian;

public class MonitorVehicleListener extends VehicleListener {

    private final Guardian plugin = Guardian.getInstance();

    public MonitorVehicleListener() {
        Bukkit.getServer().getPluginManager().registerEvent(Type.VEHICLE_CREATE, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.VEHICLE_DESTROY, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.VEHICLE_ENTER, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.VEHICLE_EXIT, this, Priority.Monitor, plugin);
    }

    @Override
    public void onVehicleCreate(VehicleCreateEvent event) {
    }

    @Override
    public void onVehicleDestroy(VehicleDestroyEvent event) {
    }

    @Override
    public void onVehicleEnter(VehicleEnterEvent event) {
    }

    @Override
    public void onVehicleExit(VehicleExitEvent event) {
    }
}
