package com.starkwiz.starkwiz.ModelClass;

public class District_ModelClass {

    String id,district_name,state_id;

    public District_ModelClass(String id, String district_name, String state_id) {
        this.id = id;
        this.district_name = district_name;
        this.state_id = state_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }
}
