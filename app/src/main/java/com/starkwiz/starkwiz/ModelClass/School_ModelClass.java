package com.starkwiz.starkwiz.ModelClass;

public class School_ModelClass {

    String id,school_name;

    public School_ModelClass(String id, String school_name) {
        this.id = id;
        this.school_name = school_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }
}
