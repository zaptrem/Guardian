package org.guardian.commands;

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
    public boolean permission() {
        return true;
    }
}
