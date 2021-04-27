package com.starkwiz.starkwiz.ModelClass;

public class Event_Subject_Modelclass {

    String id,event_id,clss,subject_name,test,test_id,subject_id,module_id,module_name,is_active;

    public Event_Subject_Modelclass(String id, String event_id, String clss, String subject_name, String test, String test_id,
                                    String subject_id, String module_id, String module_name, String is_active) {
        this.id = id;
        this.event_id = event_id;
        this.clss = clss;
        this.subject_name = subject_name;
        this.test = test;
        this.test_id = test_id;
        this.subject_id = subject_id;
        this.module_id = module_id;
        this.module_name = module_name;
        this.is_active = is_active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getClss() {
        return clss;
    }

    public void setClss(String clss) {
        this.clss = clss;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTest_id() {
        return test_id;
    }

    public void setTest_id(String test_id) {
        this.test_id = test_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
}
