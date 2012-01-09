package org.guardian.tools;

import java.util.List;
import org.guardian.params.QueryParams;

public class Tool {

    public final String name;
    public final List<String> aliases;
    public final ToolBehavior leftClickBehavior, rightClickBehavior;
    public final boolean defaultEnabled;
    public final int item;
    public final QueryParams params;
    public final ToolMode mode;
    public final boolean giveTool;

    public Tool(String name, List<String> aliases, ToolBehavior leftClickBehavior, ToolBehavior rightClickBehavior,
            boolean defaultEnabled, int item, QueryParams params, ToolMode mode, Boolean giveTool) {
        this.name = name;
        this.aliases = aliases;
        this.leftClickBehavior = leftClickBehavior;
        this.rightClickBehavior = rightClickBehavior;
        this.defaultEnabled = defaultEnabled;
        this.item = item;
        this.params = params;
        this.mode = mode;
        this.giveTool = giveTool;
    }
}
