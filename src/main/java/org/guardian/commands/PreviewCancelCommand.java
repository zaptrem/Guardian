package org.guardian.commands;

import org.guardian.util.BukkitUtils;

public class PreviewCancelCommand extends BaseCommand {

    public PreviewCancelCommand() {
        name = "preview cancel";
        usage = "<- cancel rollback preview";
    }

    @Override
    public boolean execute() {
        return true;

    }

    @Override
    public void moreHelp() {
        BukkitUtils.sendMessage(sender, "&cCancels results of a &7/hawk preview");
        BukkitUtils.sendMessage(sender, "&cOnly affects you - no changes are seen by anyony else");
    }

    @Override
    public boolean permission() {
        return true;
    }
}
