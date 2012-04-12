package org.guardian.commands;

import org.bukkit.ChatColor;
import org.guardian.PlayerSession;
import org.guardian.util.BukkitUtils;

public class ToolCommand extends BaseCommand {

    public ToolCommand() {
        name = "tool";
        usage = "<- <enable/disable> toggle all your tools";
        minArgs = 0;
        maxArgs = 1;
    }

    @Override
    public boolean execute() {
        PlayerSession session = plugin.getSessionManager().getSession(sender);
        if (!args.isEmpty()) {
            if (args.get(0).equalsIgnoreCase("enable")) {
                session.toolsEnabled = true;
            } else {
                session.toolsEnabled = false;
            }
        }
        session.toolsEnabled = !session.toolsEnabled;
        BukkitUtils.sendMessage(sender, ChatColor.GREEN + "Your tools are now " + ChatColor.GOLD + ((session.toolsEnabled) ? "enabled" : "disabled"));
        return true;
    }

    @Override
    public boolean permission() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void moreHelp() {
        // TODO Auto-generated method stub
    }
}
