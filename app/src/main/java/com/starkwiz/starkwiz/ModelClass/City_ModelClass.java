package com.starkwiz.starkwiz.ModelClass;

public class City_ModelClass {

    String  id,city_name,state_id,district_id;

    public City_ModelClass(String id, String city_name, String state_id, String district_id) {
        this.id = id;
        this.city_name = city_name;
        this.state_id = state_id;
        this.district_id = district_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }
}

