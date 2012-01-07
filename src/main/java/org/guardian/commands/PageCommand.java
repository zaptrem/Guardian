package org.guardian.commands;

import org.guardian.util.BukkitUtils;

public class PageCommand extends BaseCommand {

    public PageCommand() {
        name = "page";
        usage = "<page> <- display a page from your last search";
        allowConsole = false;
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void moreHelp() {
        BukkitUtils.sendMessage(sender, "&cShows the specified page of results from your latest search");
    }

    @Override
    public boolean permission() {
        return true;
    }
}