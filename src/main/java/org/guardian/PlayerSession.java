package org.guardian;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.guardian.entries.Entry;
import org.guardian.params.QueryParams;
import org.guardian.tools.SessionToolData;
import org.guardian.tools.Tool;

public class PlayerSession {

    private CommandSender sender;
    private Map<Tool, SessionToolData> toolDatas;
    private List<Entry> entryCache;
    private QueryParams lastQuery;
    public boolean toolsEnabled = true; // TODO this properly

    public PlayerSession(CommandSender sender) {
        this.sender = sender;
        toolDatas = new HashMap<Tool, SessionToolData>();
        if (sender instanceof Player) {
            for (final Tool tool : Guardian.getInstance().getConf().tools) {
                toolDatas.put(tool, new SessionToolData(tool, (Player) sender));
            }
        }
    }

    public CommandSender getSender() {
        return sender;
    }

    public Map<Tool, SessionToolData> getToolDatas() {
        return toolDatas;
    }

    public List<Entry> getEntryCache() {
        return entryCache;
    }

    public void setEntryCache(List<Entry> entryCache) {
        this.entryCache = entryCache;
    }

    public QueryParams getLastQuery() {
        return lastQuery;
    }

    public void setLastQuery(QueryParams lastQuery) {
        this.lastQuery = lastQuery;
    }
}
