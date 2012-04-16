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
        allowConsole = false;
        for (String toolName : plugin.getConf().toolsByName.keySet()) {
            subCommands.add(new SubToolCommand(toolName));
        }
    }

    @Override
    public boolean execute() {
        sendUsage();
        return true;
    }

    @Override
    public void moreHelp() {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean permission(CommandSender csender) {
        return true;
    }

    @Override
    public BaseCommand newInstance() {
        return new ToolCommand();
    }
    
    public class SubToolCommand extends SubCommand {
        
        public SubToolCommand(String paramName) {
            name = paramName;
            allowConsole = false;
        }

        @Override
        public boolean execute() {
            PlayerSession session = plugin.getSessionManager().getSession(sender);
            Tool byname = plugin.getConf().toolsByName.get(name);
            SessionToolData data = session.getToolDatas().get(byname);
            data.setEnabled(!data.isEnabled());
            if (data.isEnabled()) {
                ((Player) sender).getInventory().addItem(new ItemStack(byname.item, 1));
            }
            BukkitUtils.sendMessage(sender, ChatColor.GREEN + "Your tool is now " + ChatColor.GOLD + ((data.isEnabled()) ? "enabled" : "disabled"));
            return true;
        }

        @Override
        public boolean permission(CommandSender csender) {
            return csender.hasPermission("guardian.tools." + name);
        }

        @Override
        public BaseCommand newInstance() {
            return new SubToolCommand(name);
        }
        
    }
}
