package org.guardian.entries;

import java.util.List;
import org.bukkit.block.BlockState;
import org.guardian.ActionType;

public class PlayerEntry implements Entry {

    protected int playerId;
    protected String name, ip;
    protected long firstLogin, lastLogin, onlineTime, leaveTime;
    protected boolean playerLogin, playerLogout;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(long leaveTime) {
        this.leaveTime = leaveTime;
    }

    public boolean isPlayerLogin() {
        return playerLogin;
    }

    public void setPlayerLogin(boolean playerLogin) {
        this.playerLogin = playerLogin;
    }

    public boolean isPlayerLogout() {
        return playerLogout;
    }

    public void setPlayerLogout(boolean playerLogout) {
        this.playerLogout = playerLogout;
    }

    public String getMessage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ActionType getAction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<BlockState> getRollbackBlockStates() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<BlockState> getRebuildBlockStates() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isRollbacked() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
