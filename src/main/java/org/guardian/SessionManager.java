package org.guardian;

import static org.bukkit.Bukkit.getServer;
import java.util.HashMap;
import org.bukkit.command.CommandSender;

/**
 * Class for parsing managing player's {@PlayerSession}s
 * 
 * @author oliverw92, DiddiZ
 */
public class SessionManager
{
	private static HashMap<String, PlayerSession> playerSessions = new HashMap<String, PlayerSession>();

	public SessionManager() {
		// Add console session
		playerSessions.put(getServer().getConsoleSender().getName(), new PlayerSession(getServer().getConsoleSender()));
	}

	/**
	 * @return Whether the sender has a session
	 */
	public static boolean hasSession(CommandSender player) {
		return playerSessions.containsKey(player.getName());
	}

	/**
	 * Get a PlayerSession from the list, create a new session if the sender has no session
	 */
	public static PlayerSession getSession(CommandSender player) {
		PlayerSession session = playerSessions.get(player.getName());
		if (session == null) {
			session = new PlayerSession(player);
			playerSessions.put(player.getName(), session);
		}
		return session;
	}
}
