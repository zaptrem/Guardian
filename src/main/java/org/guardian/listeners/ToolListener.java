package org.guardian.listeners;

import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.guardian.Guardian;
import org.guardian.params.QueryParams;
import org.guardian.params.QueryParamsFactory;
import org.guardian.tools.SessionToolData;
import org.guardian.tools.Tool;
import org.guardian.tools.ToolBehavior;

public class ToolListener implements Listener {

    private final Guardian plugin = Guardian.getInstance();

    public ToolListener() {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.isCancelled() && event.hasBlock() && event.getMaterial() != null) {
            int type = event.getMaterial().getId();
            Tool tool = plugin.getConf().toolsByType.get(type);
            if (tool != null) {
                Player player = event.getPlayer();
                if (plugin.getConf().worlds.containsKey(player.getWorld().getName()) && player.hasPermission("guardian.tools." + tool.name)) {
                    Action action = event.getAction();
                    ToolBehavior behavior = action == Action.RIGHT_CLICK_BLOCK ? tool.rightClickBehavior : tool.leftClickBehavior;
                    SessionToolData toolData = plugin.getSessionManager().getSession(player).getToolDatas().get(tool);
                    if (behavior != ToolBehavior.NONE && toolData.isEnabled()) {
                        Block block = event.getClickedBlock();
                        QueryParams params = toolData.getParams();
                        params.loc = null;
                        params.sel = null;
                        if (behavior == ToolBehavior.BLOCK) {
                            params.loc = block.getRelative(event.getBlockFace()).getLocation();
                        } else if (block.getTypeId() != 54 || tool.params.radius != 0) {
                            params.loc = block.getLocation();
                        } else {
                            for (BlockFace face : new BlockFace[]{BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST}) {
                                if (block.getRelative(face).getTypeId() == 54) {
                                    params.sel = new CuboidSelection(event.getPlayer().getWorld(), block.getLocation(), block.getRelative(face).getLocation());
                                }
                            }
                            if (params.sel == null) {
                                params.sel = (Selection) block.getLocation();
                            }
                        }
                        player.chat(new QueryParamsFactory().parse(params));
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
