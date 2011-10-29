package org.guardian.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.guardian.Guardian;

public class GuardianCommandExecutor implements CommandExecutor {

    private Guardian plugin;
    public List<BaseCommand> commands = new ArrayList<BaseCommand>();

    public GuardianCommandExecutor(final Guardian plugin) {
        this.plugin = plugin;

        //Register commands
        commands.add(new ExampleCommand());
        commands.add(new HelpCommand());
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

        //If no arg provided for guardian command, set to help by default
        if (args.length == 0) {
            args = new String[]{"help"};
        }

        //Loop through commands to find match. Supports sub-commands
        outer:
        for (BaseCommand guardCmd : commands.toArray(new BaseCommand[0])) {
            String[] cmds = guardCmd.name.split(" ");
            for (int i = 0; i < cmds.length; i++) {
                if (i >= args.length || !cmds[i].equalsIgnoreCase(args[i])) {
                    continue outer;
                }
            }
            return guardCmd.run(plugin, sender, args, label);
        }

        //If no matches, just send help
        new HelpCommand().run(plugin, sender, args, label);
        return true;

    }
}
