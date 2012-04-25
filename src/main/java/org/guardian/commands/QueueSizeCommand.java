package org.guardian.commands;

import org.bukkit.command.CommandSender;
import org.guardian.util.BukkitUtils;

public class QueueSizeCommand extends BaseCommand {

    public QueueSizeCommand() {
        name = "queuesize";
        usage = "";
    }

    @Override
    public boolean execute() {
        BukkitUtils.sendMessage(sender, "Current queue size: " + this.plugin.getDatabaseBridge().getQueueSize());
        return true;
    }

    @Override
    public boolean permission(CommandSender csender) {
        return csender.hasPermission("guardian.rollback");
    }

    @Override
    public BaseCommand newInstance() {
        // TODO Auto-generated method stub
        return new QueueSizeCommand();
    }

}
