package org.guardian.params;

import static org.bukkit.Bukkit.getServer;
import static org.guardian.util.Utils.isInt;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.guardian.ActionType;
import org.guardian.Guardian;
import org.guardian.util.Utils;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;

/**
 * @author DiddiZ
 */
public class QueryParamsFactory
{
	@SuppressWarnings("unused")
	private final Guardian guardian;
	private final ParamsParser parser;

	private QueryParamsFactory(Guardian guardian) {
		this.guardian = guardian;
		parser = new LogBlockParamsParser(guardian);
	}

	public QueryParams create(CommandSender sender, List<String> args) throws IllegalArgumentException {
		final Map<Param, List<String>> paramsMap = parser.parse(args);
		final QueryParams params = new QueryParams();
		for (final Entry<Param, List<String>> e : paramsMap.entrySet()) {
			final List<String> paramArgs = e.getValue();
			switch (e.getKey()) {
				case ACTION:
					for (final String action : paramArgs)
						try {
							params.getActions().add(ActionType.valueOf(action.toUpperCase()));
						} catch (final IllegalArgumentException ex) {
							throw new IllegalArgumentException("There is no action '" + action + "'");
						}
					break;
				case PLAYER:
					break;
				case AREA:
					if (sender instanceof Player) {
						params.setLocation(((Player)sender).getLocation());
						if (paramArgs.size() == 0)
							params.setRadius(20); // TODO Get default value from config
						else if (isInt(paramArgs.get(0)))
							params.setRadius(Integer.valueOf(paramArgs.get(0)));
						else
							throw new IllegalArgumentException("Not a number '" + paramArgs.get(0) + "'");
					} else
						throw new IllegalArgumentException("You must be a player to use area");
					break;
				case SELECTION:
					if (sender instanceof Player) {
						final PluginManager pm = getServer().getPluginManager();
						if (pm.isPluginEnabled("WorldEdit")) {
							final Selection sel = ((WorldEditPlugin)pm.getPlugin("WorldEdit")).getSelection((Player)sender);
							if (sel != null && sel instanceof CuboidSelection)
								params.setSelection(sel);
							else
								throw new IllegalArgumentException("No selection defined ");
						} else
							throw new IllegalArgumentException("WorldEdit is not enabled");
					} else
						throw new IllegalArgumentException("You must be a player to use area");
					break;
				case BLOCK:
					for (final String block : paramArgs) {
						final Material mat = Material.matchMaterial(block);
						if (mat != null)
							params.getBlocks().add(mat.getId());
						else
							throw new IllegalArgumentException("There is no material '" + block + "'");
					}
					break;
				case WORLD:
					for (final String worldName : paramArgs) {
						final World world = getServer().getWorld(worldName);
						if (world != null)
							params.getWorlds().add(world);
						else
							throw new IllegalArgumentException("There is no world '" + worldName + "'");
					}
					break;
				case SINCE:
					final String since = Utils.join(paramArgs, " ");
					final int minutessince = Utils.parseTimeSpec(since);
					if (minutessince > 0)
						params.setSince(minutessince);
					else
						throw new IllegalArgumentException("Not a valid time spec '" + since + "'");
					break;
				case BEFORE:
					final String before = Utils.join(paramArgs, " ");
					final int minutesbefore = Utils.parseTimeSpec(before);
					if (minutesbefore > 0)
						params.setBefore(minutesbefore);
					else
						throw new IllegalArgumentException("Not a valid time spec '" + before + "'");
					break;
				case SUM:
					try {
						params.setSummarizationMode(SummarizationMode.valueOf(paramArgs.get(0).toUpperCase()));
					} catch (final IllegalArgumentException ex) {
						throw new IllegalArgumentException("There is no summarization mode '" + paramArgs.get(0) + "'");
					}
					break;
				case LIMIT:
					if (isInt(paramArgs.get(0)))
						params.setLimit(Integer.valueOf(paramArgs.get(0)));
					else
						throw new IllegalArgumentException("Not a number '" + paramArgs.get(0) + "'");
					break;
				case SILENT:
					params.setSilent(true);
					break;
				case COORDS:
					params.setNeedCoords(true);
					break;
				case MATCH:
					params.setTextMatch(Utils.join(paramArgs, " "));
					break;
				case ASC:
					params.setOrder(Order.ASC);
					break;
				case DESC:
					params.setOrder(Order.DESC);
					break;
				case LAST:
					// TODO Get last query params from session and use a close of it.
					break;
				case DESTROYED:
					params.getActions().clear();
					params.getActions().add(ActionType.DESTROYED);
					break;
				case CREATED:
					params.getActions().clear();
					params.getActions().add(ActionType.CREATED);
					break;
			}
		}
		return null;
	}
}
