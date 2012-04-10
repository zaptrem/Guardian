package org.guardian.entries;

import org.guardian.ActionType;

/**
 * Interface for basic Entry methods
 */
public interface Entry {

    public String getMessage();

    public ActionType getAction();
}
