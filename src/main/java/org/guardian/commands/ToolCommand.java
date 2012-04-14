package org.guardian.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.guardian.PlayerSession;
import org.guardian.tools.SessionToolData;
import org.guardian.tools.Tool;
import org.guardian.util.BukkitUtils;

public class ToolCommand extends BaseCommand {

    public ToolCommand() {
        name = "tool";
        usage = "<- <name> toggle the specified tool";
        minArgs = 1;
        maxArgs = 1;
        allowConsole = false;
    }

    @Override
    public boolean execute() {
        PlayerSession session = plugin.getSessionManager().getSession(sender);
        Tool byname = plugin.getConf().toolsByName.get(args.get(0));
        SessionToolData data = session.getToolDatas().get(byname);
        data.setEnabled(!data.isEnabled());
        if (data.isEnabled()) {
            ((Player) sender).getInventory().addItem(new ItemStack(byname.item, 1));
        }
        BukkitUtils.sendMessage(sender, ChatColor.GREEN + "Your tool is now " + ChatColor.GOLD + ((data.isEnabled()) ? "enabled" : "disabled"));
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

    @Override
    public boolean permission(CommandSender csender) {
        if (args.isEmpty()) {
            return true;
        } else {
            return sender.hasPermission("guardian.tools." + args.get(0));
        }
    }

    @Override
    public BaseCommand newInstance() {
        // TODO Auto-generated method stub
        return new ToolCommand();
    }
}
