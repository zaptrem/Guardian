package org.guardian.entries;

import org.guardian.ActionType;

/**
 * Interface for basic Entry methods
 */
public interface Entry extends Rollbackable {

    public String getMessage();

    public ActionType getAction();
}
