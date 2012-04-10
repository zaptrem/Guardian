package org.guardian.commands;

import org.bukkit.ChatColor;
import org.guardian.util.BukkitUtils;
import org.guardian.util.Utils;

public class PageCommand extends BaseCommand {

    public PageCommand() {
        name = "page";
        usage = "<page> <- display a page from your last search";
        allowConsole = false;
        minArgs = 1;
        maxArgs = 1;
    }

    @Override
    public boolean execute() {
        if (Utils.isInt(args.get(0)))
            showPage(Integer.valueOf(args.get(0)));
        else
            sender.sendMessage(ChatColor.RED + "You have to specify a page");
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