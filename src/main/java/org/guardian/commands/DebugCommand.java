package org.guardian.commands;

import java.sql.SQLException;

import org.guardian.ActionType;
import org.guardian.params.QueryParams;
import org.guardian.params.SummarizationMode;

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
			plugin.getDatabaseBridge().getEntries(test);
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
