package org.guardian.entries;

import java.text.SimpleDateFormat;
import java.util.List;
import org.bukkit.Location;
import org.guardian.ActionType;

public abstract class DataEntry implements Entry {

    protected int id;
    protected long date;
    protected static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
    protected ActionType action;
    protected String playerName;
    protected String worldName;
    protected Location loc;
    protected boolean rollbacked;
    protected String pluginName;
    protected List<DataEntry> children;

    protected DataEntry(ActionType action, String playerName, Location loc, String worldName, long date, String pluginName) {
        this(-1, date, action, playerName, worldName, loc, false, pluginName, null);
    }

    protected DataEntry(int id, long date, ActionType action, String playerName, String worldName, Location loc, boolean rollbacked, String pluginName, List<DataEntry> children) {
        this.id = id;
        this.date = date;
        this.action = action;
        this.playerName = playerName;
        this.worldName = worldName;
        this.loc = loc;
        this.rollbacked = rollbacked;
        this.pluginName = pluginName;
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public long getDate() {
        return date / 1000L;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public Location getLocation() {
        return loc;
    }

    public void setLocation(Location loc) {
        this.loc = loc;
    }

    public boolean isRollbacked() {
        return rollbacked;
    }

    public void setRollbacked(boolean rollbacked) {
        this.rollbacked = rollbacked;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public boolean hasChildren() {
        return children != null && children.size() > 0;
    }

    public List<DataEntry> getChildren() {
        return children;
    }

    public void setChildren(List<DataEntry> children) {
        this.children = children;
    }
}
