package org.guardian.commands;

import org.bukkit.command.CommandSender;
import org.guardian.util.BukkitUtils;

public class UndoCommand extends BaseCommand {

    public UndoCommand() {
        name = "undo";
        usage = "<- reverses your previous rollback";
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void moreHelp() {
        BukkitUtils.sendMessage(sender, "&cReverses your previous rollback if you made a mistake with it");
    }

    @Override
    public boolean permission(CommandSender csender) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public BaseCommand newInstance() {
        // TODO Auto-generated method stub
        return new UndoCommand();
    }
}
