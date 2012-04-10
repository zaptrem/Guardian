package org.guardian.commands;

import java.sql.SQLException;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.guardian.entries.Entry;
import org.guardian.params.QueryParams;
import org.guardian.params.QueryParamsFactory;
import org.guardian.util.BukkitUtils;

public class SearchCommand extends BaseCommand {

    public SearchCommand() {
        name = "search";
        usage = "<parameters> <- search Guardian database";
    }

    @Override
    public boolean execute() {
        final QueryParams params = new QueryParamsFactory().create(sender, args);
        Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {

            public void run() {
                BukkitUtils.sendMessage(sender, ChatColor.BLUE + "Searching for entries");
                try {
                    List<Entry> results = plugin.getDatabaseBridge().getEntries(params);
                    BukkitUtils.info("Found " + results.size() + " entries!");
                    for (Entry tempEntry : results) {
                        BukkitUtils.sendMessage(sender, ChatColor.RED + tempEntry.getMessage());
                    }
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        return true;
    }

    public void moreHelp() {
        //TODO display help
    }

    @Override
    public boolean permission() {
        return true;
    }
}
