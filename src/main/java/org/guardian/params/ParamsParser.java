package org.guardian.params;

import java.util.List;
import java.util.Map;

public interface ParamsParser {

    /**
     * Extracts all parameters with its arguments
     */
    public Map<Param, List<String>> parse(List<String> args) throws IllegalArgumentException;

    /**
     * @return Whether a words is just a misspelled command (/g rllback ...) or
     * a lookup command (/g player diddiz)
     */
    public boolean isKeyWord(String arg);
}
