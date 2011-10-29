package org.guardian;

import org.bukkit.command.CommandSender;

public class PlayerSession
{
	private final CommandSender sender;

	public PlayerSession(CommandSender sender) {
		this.sender = sender;
	}

	public CommandSender getSender() {
		return sender;
	}
}
