package org.guardian.commands;

import org.guardian.util.BukkitUtils;

public class SearchCommand extends BaseCommand {

    public SearchCommand() {
        name = "search";
        usage = "<parameters> <- search HawkEye database";
    }

    @Override
    public boolean execute() {
        return true;

    }

    public void moreHelp() {
    	
    	//TODO display help
    	
    }

    @Override
    public boolean permission() {
        return true;
    }
}