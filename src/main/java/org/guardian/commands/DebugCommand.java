package org.guardian.commands;

import java.sql.SQLException;
import java.util.List;

import org.guardian.ActionType;
import org.guardian.entries.Entry;
import org.guardian.params.QueryParams;
import org.guardian.params.SummarizationMode;
import org.guardian.util.BukkitUtils;

public class DebugCommand extends BaseCommand {

    public DebugCommand() {
        name = "debug";
        usage = "";
        minArgs = 0;
        maxArgs = 0;
    }

    @Override
    public boolean execute() {
        QueryParams test = new QueryParams();
        test.actions.add(ActionType.BLOCK_BREAK);
        test.sum = SummarizationMode.NONE;
        test.needSubTableBlock = true;
        test.needPlayer = true;
        try {
            List<Entry> temp = plugin.getDatabaseBridge().getEntries(test);
            BukkitUtils.info("Found " + temp.size() + " entries!");
            for(Entry tempEntry : temp) {
                BukkitUtils.info(tempEntry.getMessage());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean permission() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void moreHelp() {
        // TODO Auto-generated method stub

    }

}
