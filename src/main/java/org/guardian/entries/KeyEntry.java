package org.guardian.entries;

/*
 * Used for plugins and worlds -md_5
 */
public class KeyEntry implements Entry {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getMessage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
