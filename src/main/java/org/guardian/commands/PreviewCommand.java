package org.guardian.commands;

import org.bukkit.command.CommandSender;
import org.guardian.util.BukkitUtils;

public class PreviewCommand extends BaseCommand {

    public PreviewCommand() {
        name = "preview";
        usage = "<parameters> <- preview rollback changes";
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void moreHelp() {
        BukkitUtils.sendMessage(sender, "&cPreviews a rollback to only you");
        BukkitUtils.sendMessage(sender, "&cThis type of rollback does not affect the actual world in any way");
        BukkitUtils.sendMessage(sender, "&cThe effects can be applied after using &7/hawk preview apply&c or cancelled using &7/hawk preview cancel");
        BukkitUtils.sendMessage(sender, "&cThe parameters are the same as &7/hawk rollback");
    }

    @Override
    public boolean permission() {
        return true;
    }

    @Override
    public boolean permission(CommandSender csender) {
        // TODO Auto-generated method stub
        return true;
    }
}
