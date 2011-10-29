package org.guardian;

import org.bukkit.command.CommandSender;

public class PlayerSession {

	private CommandSender sender;
	
	public PlayerSession(CommandSender sender) {
		this.sender = sender;
	}

	public CommandSender getSender() {
		return sender;
	}
	public void setSender(CommandSender sender) {
		this.sender = sender;
	}
	
}
