package org.guardian.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.guardian.Guardian;
import org.guardian.PlayerSession;
import org.guardian.util.BukkitUtils;
import org.guardian.util.Utils;

public class PageCommand extends BaseCommand {

    public PageCommand() {
        name = "page";
        usage = "<page> <- view a page from your search";
        allowConsole = false;
        minArgs = 1;
        maxArgs = 1;
    }

    @Override
    public boolean execute() {
        if (Utils.isInt(args.get(0))) {
            showPage(sender, Integer.valueOf(args.get(0)));
        } else {
            sender.sendMessage(ChatColor.RED + "You have to specify a page");
        }
        return true;
    }

    @Override
    public void moreHelp() {
        BukkitUtils.sendMessage(sender, "&cShows the specified page of results from your latest search");
    }

    @Override
    public boolean permission(CommandSender csender) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public BaseCommand newInstance() {
        return new PageCommand();
    }
    
    public static void showPage(CommandSender sender, int page) {
        Guardian plugin = Guardian.getInstance();
        PlayerSession session = plugin.getSessionManager().getSession(sender);
        if (session.getEntryCache() != null && session.getEntryCache().size() > 0) {
            final int startpos = (page - 1) * plugin.getConf().linesPerPage;
            if (page > 0 && startpos <= session.getEntryCache().size() - 1) {
                final int stoppos = startpos + plugin.getConf().linesPerPage >= session.getEntryCache().size() ? session.getEntryCache().size() - 1 : startpos + plugin.getConf().linesPerPage - 1;
                final int numberOfPages = (int) Math.ceil(session.getEntryCache().size() / (double) plugin.getConf().linesPerPage);
                if (numberOfPages != 1) {
                    sender.sendMessage(ChatColor.DARK_AQUA + "Page " + page + "/" + numberOfPages);
                }
                for (int i = startpos; i <= stoppos; i++) {
                    sender.sendMessage(ChatColor.GOLD + session.getEntryCache().get(i).getMessage());
                }
            } else {
                sender.sendMessage(ChatColor.RED + "There isn't a page '" + page + "'");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "No blocks in lookup cache");
        }
    }
}
