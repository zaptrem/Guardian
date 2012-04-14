package org.guardian.commands;

import org.bukkit.command.CommandSender;
import org.guardian.Guardian;
import org.guardian.params.QueryParams;

public abstract class AbstractCommand implements Runnable {

    protected CommandSender sender;
    protected QueryParams params;
    protected Guardian plugin;

    protected AbstractCommand(CommandSender sender, QueryParams params, boolean async) throws Exception {
        this.sender = sender;
        this.params = params;
        this.plugin = Guardian.getInstance();
        if (async) {
            if (plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, this) == -1) {
                throw new Exception("Failed to schedule the command");
            }
        } else {
            run();
        }
    }
}
