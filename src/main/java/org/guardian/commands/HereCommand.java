package org.guardian.commands;

import org.bukkit.command.CommandSender;
import org.guardian.util.BukkitUtils;

public class HereCommand extends BaseCommand {

    public HereCommand() {
        name = "here";
        usage = "[radius] [player] <- search around you";
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void moreHelp() {
        BukkitUtils.sendMessage(sender, "&cShows all changes in a radius around you");
        BukkitUtils.sendMessage(sender, "&cRadius should be an integer");
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
