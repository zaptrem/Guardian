package org.guardian.commands;

import org.guardian.util.BukkitUtils;

public class RollbackCommand extends BaseCommand {

    public RollbackCommand() {
        name = "rollback";
        usage = "<parameters> <- rollback changes";
    }

    @Override
    public boolean execute() {
        return true;

    }

    @Override
    public void moreHelp() {
        List<String> acs = new ArrayList<String>();
        for (DataType type : DataType.values()) {
            if (type.canRollback()) {
                acs.add(type.getConfigName());
            }
        }
        BukkitUtils.sendMessage(sender, "&7There are 6 parameters you can use - &ca: p: w: r: f: t:");
        BukkitUtils.sendMessage(sender, "&6Action &ca:&7 - list of actions separated by commas. Select from the following: &8" + BukkitUtils.join(acs, " "));
        BukkitUtils.sendMessage(sender, "&6Player &cp:&7 - list of players. &6World &cw:&7 - list of worlds");
        BukkitUtils.sendMessage(sender, "&6Filter &cf:&7 - list of keywords (e.g. block id)");
        BukkitUtils.sendMessage(sender, "&6Radius &cr:&7 - radius to search around given location");
        BukkitUtils.sendMessage(sender, "&6Time &ct:&7 - time bracket in the following format:");
        BukkitUtils.sendMessage(sender, "&7  -&c t:10h45m10s &7-back specified amount of time");
        BukkitUtils.sendMessage(sender, "&7  -&c t:2011-06-02,10:45:10 &7-from given date");
        BukkitUtils.sendMessage(sender, "&7  -&c t:2011-06-02,10:45:10,2011-07-04,18:15:00 &7-between dates");
    }

    @Override
    public boolean permission() {
        return;
    }
}