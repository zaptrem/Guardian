package org.guardian.params;

public enum Param {
    ACTION(1, 1), PLAYER(1, -1), AREA(0, 1), SELECTION, BLOCK(1, -1), WORLD(1, -1), SINCE(1, -1), BEFORE(1, -1), SUM(1, 1), LIMIT(1, 1), SILENT, COORDS, MATCH(1, -1), ASC, DESC, LAST, DESTROYED, CREATED;

    private final int minArgs, maxArgs;

    Param() {
        this(0, 0);
    }

    Param(int minArgs, int maxArgs) {
        this.minArgs = minArgs;
        this.maxArgs = maxArgs;
    }

    public int getMinArgs() {
        return minArgs;
    }

    public int getMaxArgs() {
        return maxArgs;
    }
}
