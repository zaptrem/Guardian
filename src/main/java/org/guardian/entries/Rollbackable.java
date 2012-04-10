package org.guardian.entries;

import java.util.List;
import org.bukkit.block.BlockState;

public interface Rollbackable {
    public List<BlockState> getRollbackBlockStates();

    public List<BlockState> getRebuildBlockStates();
}
