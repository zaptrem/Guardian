package org.guardian.entries;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.guardian.ActionType;

public class TextEntry extends DataEntry {

    private String text;

    public TextEntry(ActionType action, String playerName, Location loc, String worldName, long date, String pluginName) {
        super(action, playerName, loc, worldName, date, pluginName);
    }

    public String getText() {
        return text;
    }

    @Override
    public String getMessage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BlockState> getRollbackBlockStates() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BlockState> getRebuildBlockStates() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
