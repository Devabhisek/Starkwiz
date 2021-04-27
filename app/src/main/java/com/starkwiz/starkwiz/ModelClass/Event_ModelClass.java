package com.starkwiz.starkwiz.ModelClass;

public class Event_ModelClass {

    String id,event_name,event_start_date,event_end_date,is_active;

    public Event_ModelClass(String id, String event_name,
                            String event_start_date, String event_end_date, String is_active) {
        this.id = id;
        this.event_name = event_name;
        this.event_start_date = event_start_date;
        this.event_end_date = event_end_date;
        this.is_active = is_active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_start_date() {
        return event_start_date;
    }

    public void setEvent_start_date(String event_start_date) {
        this.event_start_date = event_start_date;
    }

    public String getEvent_end_date() {
        return event_end_date;
    }

    public void setEvent_end_date(String event_end_date) {
        this.event_end_date = event_end_date;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
}
