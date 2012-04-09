package org.guardian.params;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.World;
import org.guardian.ActionType;
import com.sk89q.worldedit.bukkit.selections.Selection;

/**
 * @author DiddiZ
 */
public class QueryParams implements Cloneable {
	public List<ActionType> actions = new ArrayList<ActionType>();
	public long before, since;
	public List<Integer> blocks;
	public boolean excludePlayersMode;
	public int limit;
	public Location loc;
	public Order order = Order.ASC;
	public List<String> players = new ArrayList<String>();
	public int radius;
	public Selection sel;
	public boolean silent;
	public SummarizationMode sum;
	public String textMatch;
	public List<World> worlds;
	public boolean needId = false, needDate = false, needPlayer = false, needCoords = false, needSubTableText = false,
			needSubTableItem = false, needSubTableBlock = false, needSubTableDeath = false;

	/**
	 * Constructs a new QueryParams with default values
	 */
	public QueryParams() {
		// TODO Set default values
	}

	@Override
	public QueryParams clone() {
		// TODO
		return null;
	}

}
