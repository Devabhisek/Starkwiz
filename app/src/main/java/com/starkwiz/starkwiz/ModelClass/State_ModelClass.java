package com.starkwiz.starkwiz.ModelClass;

public class State_ModelClass {

    String id,state_name,state_code;

    public State_ModelClass(String id, String state_name, String state_code) {
        this.id = id;
        this.state_name = state_name;
        this.state_code = state_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }
}
