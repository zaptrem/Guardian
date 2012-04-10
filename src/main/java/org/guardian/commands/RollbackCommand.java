package org.guardian.commands;

import java.util.ArrayList;
import java.util.List;

import org.guardian.ActionType;
import org.guardian.util.BukkitUtils;

public class RollbackCommand extends BaseCommand {

    public RollbackCommand() {
        name = "rollback";
        usage = "<parameters> <- rollback changes";
    }

    @Override
    public boolean execute() {
        return true;

    }

    @Override
    public void moreHelp() {
        // TODO display help

    }

    @Override
    public boolean permission() {
        return true;
    }
}