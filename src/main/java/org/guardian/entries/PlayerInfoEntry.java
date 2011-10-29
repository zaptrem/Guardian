package org.guardian.entries;


/**
 * Represents the data in players table
 * 
 * @author DiddiZ
 */
public class PlayerInfoEntry implements Entry
{
	private int id;
	private String name, ip, group;
	private long firstLogin, lastLogin, onlineTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(long firstLogin) {
		this.firstLogin = firstLogin;
	}

	public long getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(long lastLogin) {
		this.lastLogin = lastLogin;
	}

	public long getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(long onlineTime) {
		this.onlineTime = onlineTime;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
