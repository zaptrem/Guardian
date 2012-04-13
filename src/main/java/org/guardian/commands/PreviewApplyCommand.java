package org.guardian.commands;

import org.bukkit.command.CommandSender;
import org.guardian.util.BukkitUtils;

public class PreviewApplyCommand extends BaseCommand {

    public PreviewApplyCommand() {
        name = "preview apply";
        usage = "<- apply rollback preview";
    }

    @Override
    public boolean execute() {
        return true;

    }

    @Override
    public void moreHelp() {
        BukkitUtils.sendMessage(sender, "&cApplies the results of a &7/hawk preview&c globally");
        BukkitUtils.sendMessage(sender, "&cUntil this command is called, the preview is only visible to you");
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
