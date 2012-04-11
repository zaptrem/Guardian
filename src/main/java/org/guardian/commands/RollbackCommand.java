package org.guardian.commands;

import java.sql.SQLException;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.guardian.Rollback;
import org.guardian.entries.Entry;
import org.guardian.params.QueryParams;
import org.guardian.params.QueryParamsFactory;
import org.guardian.util.BukkitUtils;

public class RollbackCommand extends BaseCommand {

    public RollbackCommand() {
        name = "rollback";
        usage = "<parameters> <- rollback changes";
    }

    @Override
    public boolean execute() {
        final QueryParams params = new QueryParamsFactory().create(sender, args);
        Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {

            public void run() {
                BukkitUtils.sendMessage(sender, ChatColor.BLUE + "Searching for entries");
                try {
                    List<Entry> results = plugin.getLog(params);
                    BukkitUtils.info("Found " + results.size() + " entries!");
                    plugin.rollback(results);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
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