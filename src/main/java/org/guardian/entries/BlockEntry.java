package org.guardian.entries;

import java.util.List;
import org.bukkit.block.BlockState;
import org.guardian.util.BukkitUtils;

public class BlockEntry extends DataEntry {

    private int typeFrom, typeTo;
    private byte dataFrom, dataTo;

    public int getTypeBefore() {
        return typeFrom;
    }

    public void setTypeBefore(int typeBefore) {
        this.typeFrom = typeBefore;
    }

    public int getTypeAfter() {
        return typeTo;
    }

    public void setTypeAfter(int typeAfter) {
        this.typeTo = typeAfter;
    }

    public byte getDataBefore() {
        return dataFrom;
    }

    public void setDataBefore(byte dataBefore) {
        this.dataFrom = dataBefore;
    }

    public byte getDataAfter() {
        return dataTo;
    }

    /**
     * Returns the human readable form of a block data entry in the following
     * format: %DATE% %PLAYERNAME% destroyed %BLOCK% created %BLOCK% replaced
     * %BLOCK% at %LOCATION%
     */
    @Override
    public String getMessage() {
        final StringBuilder msg = new StringBuilder();
        if (date > 0) {
            msg.append(dateFormat.format(date));
            msg.append(" ");
        }
        if (playerName != null) {
            msg.append(playerName);
            msg.append(" ");
        }
        if (typeTo == 0) {
            msg.append("destroyed ");
            msg.append(BukkitUtils.materialName(typeFrom, dataFrom));

        } else if (typeFrom == 0) {
            msg.append("created ");
            msg.append(BukkitUtils.materialName(typeTo, dataTo));
        } else {
            msg.append("replaced ");
            msg.append(BukkitUtils.materialName(typeFrom, dataFrom));
            msg.append(" with ");
            msg.append(BukkitUtils.materialName(typeTo, dataTo));
        }
        if (loc != null) {
            msg.append(" at ");
            msg.append(loc.getBlockX());
            msg.append(":");
            msg.append(loc.getBlockY());
            msg.append(":");
            msg.append(loc.getBlockZ());
        }
        return msg.toString();
    }

    @Override
    public List<BlockState> getRollbackBlockStates() {
        throw new UnsupportedOperationException("Not supported yet."); //TODO
    }

    @Override
    public List<BlockState> getRebuildBlockStates() {
        throw new UnsupportedOperationException("Not supported yet."); //TODO
    }
}
