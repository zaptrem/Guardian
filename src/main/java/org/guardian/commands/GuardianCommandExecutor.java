package org.guardian.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.guardian.Guardian;

public class GuardianCommandExecutor implements CommandExecutor {

    private Guardian plugin;

    public GuardianCommandExecutor(final Guardian plugin) {
        this.plugin = plugin;
    }

    /**
     * Command manager for Guardian
     * @param sender - {@link CommandSender}
     * @param command - {@link Command}
     * @param Label - String
     * @param args[] - String[]
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            return onPlayerCommand((Player) sender, command, label, args);
        } else {
            return onConsoleCommand(sender, command, label, args);
        }
    }

    /**
     * Player command manager for Guardian
     * @param sender - {@link Player}
     * @param command - {@link Command}
     * @param Label - String
     * @param args[] - String[]
     */
    private boolean onPlayerCommand(Player player, Command command, String label, String[] args) {
        // TODO
        player.sendMessage(plugin.getDescription().getCommands().toString());
        return true;
    }

    /**
     * Console manager for Guardian
     * @param sender - {@link CommandSender}
     * @param command - {@link Command}
     * @param Label - String
     * @param args[] - String[]
     */
    private boolean onConsoleCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO
        return true;
    }
}
