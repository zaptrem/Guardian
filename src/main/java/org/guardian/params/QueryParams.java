package org.guardian.params;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.World;
import org.guardian.ActionType;
import com.sk89q.worldedit.bukkit.selections.Selection;

/**
 * @author DiddiZ
 */
public class QueryParams implements Cloneable {
	public List<ActionType> actions;
	public long before, since;
	public List<Integer> blocks;
	public boolean excludePlayersMode;
	public int limit;
	public Location loc;
	public Order order;
	public List<String> players;
	public int radius;
	public Selection sel;
	public boolean silent;
	public SummarizationMode sum;
	public String textMatch;
	public List<World> worlds;
	public boolean needId, needDate, needPlayer, needCoords, needSubTableText, needSubTableItem, needSubTableBlock, needSubTableDeath;

	/**
	 * Constructs a new QueryParams with default values
	 */
	QueryParams() {
		// TODO Set default values
	}

	@Override
	public QueryParams clone() {
		// TODO
		return null;
	}

}
