package org.guardian.commands;

import org.bukkit.ChatColor;
import org.guardian.util.BukkitUtils;

public class HelpCommand extends BaseCommand {

    public HelpCommand() {
        name = "help";
        maxArgs = 1;
        minArgs = 0;
        usage = "<- lists all Guardian commands";
    }

    @Override
    public boolean execute() {
        // General help
        if (args.isEmpty()) {
            BukkitUtils.sendMessage(sender, ChatColor.AQUA + "---------------------- Guardian v" + plugin.getDescription().getVersion() + "----------------------");
            BukkitUtils.sendMessage(sender, "Type /guardian help <command> for more info on that command");
            for (BaseCommand cmd : plugin.getCommandExecutor().getCommands().toArray(new BaseCommand[0])) {
                if (cmd.permission()) {
                    BukkitUtils.sendMessage(sender, "- " + ChatColor.GREEN + "/" + usedCommand + " " + cmd.name + ChatColor.GREEN + " " + cmd.usage);
                }
            }
        } // Command-specific help
        else {
            for (BaseCommand cmd : plugin.getCommandExecutor().getCommands().toArray(new BaseCommand[0])) {
                if (cmd.permission() && cmd.name.equalsIgnoreCase(args.get(0))) {
                    BukkitUtils.sendMessage(sender, "---------------------- Guardian - " + cmd.name);
                    BukkitUtils.sendMessage(sender, "- " + ChatColor.GREEN + "/" + usedCommand + " " + cmd.name + ChatColor.GREEN + " " + cmd.usage);
                    cmd.sender = sender;
                    cmd.moreHelp();
                    return true;
                }
            }
            BukkitUtils.sendMessage(sender, "No command found by that name");
        }
        return true;
    }

    @Override
    public void moreHelp() {
        BukkitUtils.sendMessage(sender, ChatColor.AQUA + "Shows all Guardian commands");
        BukkitUtils.sendMessage(sender, ChatColor.RED + "Type " + ChatColor.GRAY + "/guardian help <command>" + ChatColor.RED + " for help on that command");
    }

    @Override
    public boolean permission() {
        return true;
    }
}
