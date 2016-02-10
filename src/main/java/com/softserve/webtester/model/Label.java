package com.softserve.webtester.model;

import java.io.Serializable;

/**
 * The Label class represents {@code Label} entity stored in the database.
 *
 * @author Anton Mykytiuk
 * @version 1.0
 */

public class Label implements Serializable {

    private static final long serialVersionUID = 8470321606695180897L;

    private int id;
    private String name;

    public Label() { }

    public Label(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
