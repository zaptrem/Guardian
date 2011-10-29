package org.guardian.commands;

import org.guardian.util.BukkitUtils;

public class HelpCommand extends BaseCommand {

	public HelpCommand() {
		name = "help";
		maxArgs = 1;
		minArgs = 0;
		usage = "<- lists all Guardian commands";
	}
	
	public boolean execute() {
		//General help
		if (args.size() == 0) {
			BukkitUtils.sendMessage(sender, "---------------------- Guardian ----------------------");
			BukkitUtils.sendMessage(sender, "Type /guardian help <command> for more info on that command");
			for (BaseCommand cmd : plugin.commandExecutor.commands.toArray(new BaseCommand[0]))
				if (cmd.permission())
					BukkitUtils.sendMessage(sender, "- /"+usedCommand+" " + cmd.name + " " + cmd.usage);
		}
		//Command-specific help
		else {
			for (BaseCommand cmd : plugin.commandExecutor.commands.toArray(new BaseCommand[0])) {
				if (cmd.permission() && cmd.name.equalsIgnoreCase(args.get(0))) {
					BukkitUtils.sendMessage(sender, "---------------------- Guardian - " + cmd.name);
					BukkitUtils.sendMessage(sender, "- /"+usedCommand+" " + cmd.name + " " + cmd.usage);
					cmd.sender = sender;
					cmd.moreHelp();
					return true;
				}
			}
			BukkitUtils.sendMessage(sender, "No command found by that name");
		}
		return true;
	}
	
	public void moreHelp() {
		BukkitUtils.sendMessage(sender, "&cShows all Guardian commands");
		BukkitUtils.sendMessage(sender, "&cType &7/guardian help <command>&c for help on that command");
	}
	
	public boolean permission() {
		return true;
	}

}