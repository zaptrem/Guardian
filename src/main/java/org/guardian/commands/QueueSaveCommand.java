package org.guardian.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.guardian.DatabaseBridge;
import org.guardian.Guardian;

public class QueueSaveCommand extends BaseCommand {

    public QueueSaveCommand() {
        name = "queuesave";
        usage = "";
    }

    @Override
    public boolean execute() {
        plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, asTask(sender, plugin));
        return true;
    }

    private Runnable asTask(final CommandSender sender, final Guardian plugin) {
        return new Runnable() {

            @Override
            public void run() {
                final DatabaseBridge consumer = plugin.getDatabaseBridge();
                if (consumer.getQueueSize() > 0) {
                    sender.sendMessage(ChatColor.DARK_AQUA + "Current queue size: " + consumer.getQueueSize());
                    int lastSize = -1, fails = 0;
                    while (consumer.getQueueSize() > 0) {
                        fails = lastSize == consumer.getQueueSize() ? fails + 1 : 0;
                        if (fails > 10) {
                            sender.sendMessage(ChatColor.RED + "Unable to save queue");
                            return;
                        }
                        lastSize = consumer.getQueueSize();
                        consumer.run();
                    }
                    sender.sendMessage(ChatColor.GREEN + "Queue saved successfully");
                }
            }
            
        };
    }

    @Override
    public boolean permission(CommandSender csender) {
        return csender.hasPermission("guardian.rollback");
    }

    @Override
    public BaseCommand newInstance() {
        return new QueueSaveCommand();
    }

}
