package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.guardian.Consumer;
import org.guardian.Guardian;

public abstract class LoggingListener implements Listener {

    protected final Guardian guardian = Guardian.getInstance();
    protected final Consumer consumer = guardian.getConsumer();

    protected LoggingListener() {
        Bukkit.getServer().getPluginManager().registerEvents(this, guardian);
    }
}
