package org.guardian.entries;

import java.util.List;
import org.bukkit.block.BlockState;

public class TextEntry extends DataEntry {

    private String text;

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
