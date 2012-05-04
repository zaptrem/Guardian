package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.guardian.DatabaseBridge;
import org.guardian.Guardian;

public abstract class LoggingListener implements Listener {

    protected final String ENVIRONMENT = "Environment";
    protected final String DISPENSER = "Dispenser";
    protected final String PISTON = "Piston";
    protected final String PLUGIN = "Guardian";
    protected final Guardian guardian = Guardian.getInstance();
    protected final DatabaseBridge consumer = Guardian.getInstance().getDatabaseBridge();

    protected LoggingListener() {
        Bukkit.getServer().getPluginManager().registerEvents(this, guardian);
    }
}
