package com.starkwiz.starkwiz.ModelClass;

public class Hublist_ModelClass {
    String id,hub_type;

    public Hublist_ModelClass(String id, String hub_type) {
        this.id = id;
        this.hub_type = hub_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHub_type() {
        return hub_type;
    }

    public void setHub_type(String hub_type) {
        this.hub_type = hub_type;
    }
}
