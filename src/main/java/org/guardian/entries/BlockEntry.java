package org.guardian.entries;

import static org.guardian.util.BukkitUtils.materialName;
import java.util.List;
import org.bukkit.block.BlockState;

public class BlockEntry extends DataEntry
{

	private int typeBefore, typeAfter;
	private byte dataBefore, dataAfter;

	public int getTypeBefore() {
		return typeBefore;
	}

	public void setTypeBefore(int typeBefore) {
		this.typeBefore = typeBefore;
	}

	public int getTypeAfter() {
		return typeAfter;
	}

	public void setTypeAfter(int typeAfter) {
		this.typeAfter = typeAfter;
	}

	public byte getDataBefore() {
		return dataBefore;
	}

	public void setDataBefore(byte dataBefore) {
		this.dataBefore = dataBefore;
	}

	public byte getDataAfter() {
		return dataAfter;
	}

	public void setDataAfter(byte dataAfter) {
		this.dataAfter = dataAfter;
	}

	@Override
	public List<BlockState> getRollbackBlockStates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlockState> getRebuildBlockStates() {
		return null;
	}

	@Override
	/**
	 * Returns the human readable form of a block data entry in the following format:
	 * %DATE% %PLAYERNAME% destroyed %BLOCK% created %BLOCK% replaced %BLOCK% at %LOCATION%
	 */
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
		if (typeAfter == 0) {
			msg.append("destroyed ");
			msg.append(materialName(typeBefore, dataBefore));

		} else if (typeBefore == 0) {
			msg.append("created ");
			msg.append(materialName(typeAfter, dataAfter));
		} else {
			msg.append("replaced ");
			msg.append(materialName(typeBefore, dataBefore));
			msg.append(" with ");
			msg.append(materialName(typeAfter, dataAfter));
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
}
