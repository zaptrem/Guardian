package org.guardian.commands;

import org.guardian.util.BukkitUtils;

public class TptoCommand extends BaseCommand {

    public TptoCommand() {
        name = "tpto";
        usage = "<id> <- teleport to location of the data entry";
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void moreHelp() {
        BukkitUtils.sendMessage(sender, "&cTakes you to the location of the data entry with the specified ID");
        BukkitUtils.sendMessage(sender, "&cThe ID can be found in either the DataLog interface or when you do a search command");
    }

    @Override
    public boolean permission() {
        return true;
    }
}