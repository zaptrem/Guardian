package org.guardian.tools;

public enum ToolMode {

    CLEARLOG("guardian.clearlog"),
    LOOKUP("guardian.lookup"),
    REDO("guardian.redo"),
    ROLLBACK("guardian.rollback"),
    UNDO("guardian.undo"),
    WRITELOGFILE("guardian.writelog");
    private final String permission;

    private ToolMode(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
