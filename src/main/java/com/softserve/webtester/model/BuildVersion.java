package com.softserve.webtester.model;

import java.io.Serializable;

/**
 * The BuildVersion class represents {@code BuilVersion} entity stored in the database.
 *
 * @author Anton Mykytiuk
 * @version 1.0
 */

public class BuildVersion extends AbstractDomain implements Serializable {

    private static final long serialVersionUID = 5450435696043755309L;

    public BuildVersion() { }

    public BuildVersion(String name, String description) {
        super(name, description);
    }

    public BuildVersion(int id, String name, String description) {
        super(id, name, description);
    }

    public BuildVersion(int id, String name, String description, boolean deleted) {
        super(id, name, description, deleted);
    }
}
