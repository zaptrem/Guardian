package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.guardian.Guardian;
import org.guardian.commands.BaseCommand;
import org.guardian.commands.SearchCommand;
import org.guardian.commands.SearchCommand.CommandSearch;
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
        if (event.hasBlock() && event.getMaterial() != null) {
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
                        QueryParams params = new QueryParamsFactory().create(player, toolData.getParams());
                        params.loc = null;
                        params.sel = null;
                        if (behavior == ToolBehavior.BLOCK) {
                            params.loc = block.getRelative(event.getBlockFace()).getLocation();
                        } else if (behavior == ToolBehavior.TOOL) {
                            params.loc = event.getClickedBlock().getLocation();
                        } else if (params.radius != 0) {
                            params.loc = block.getLocation();
                        }
                        try {
                            for (BaseCommand guardCmd : plugin.getCommandExecutor().getCommands().toArray(new BaseCommand[0])) {
                                if (guardCmd instanceof SearchCommand) {
                                    ((SearchCommand) guardCmd).new CommandSearch(player, params, true);
                                }
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
