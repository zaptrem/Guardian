package org.guardian.entries;

import org.guardian.ActionType;

/**
 * Interface for basic Entry methods
 * 
 * @author DiddiZ
 */
public interface Entry {
    public String getMessage();

    public ActionType getAction();
}
