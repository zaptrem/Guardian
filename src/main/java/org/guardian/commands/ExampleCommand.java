package org.guardian.commands;

import org.guardian.util.BukkitUtils;

public class ExampleCommand extends BaseCommand {

    public ExampleCommand() {
        name = "example";
        usage = "<req param> [opt param] <- this is an example command";
        minArgs = 1;
        maxArgs = 2;
    }

    @Override
    public boolean execute() {
        plugin.getServer().broadcastMessage("Hello this an example: " + args.get(0));
        if (args.size() > 1) {
            plugin.getServer().broadcastMessage("Extra parameter! " + args.get(1));
        }
        return true;
    }

    @Override
    public boolean permission() {
        return true;
    }

    @Override
    public void moreHelp() {
        BukkitUtils.sendMessage(sender, "This is an example extra help message");
        BukkitUtils.sendMessage(sender, "You use it like this: /example <derp> [herp]");
    }
}
