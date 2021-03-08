package com.starkwiz.starkwiz.ModelClass;

public class Schedule_ModelClass {

    String id,test_id,subject_id,module_id,date,time,month;

    public Schedule_ModelClass(String id, String test_id, String subject_id,
                               String module_id, String date, String time, String month) {
        this.id = id;
        this.test_id = test_id;
        this.subject_id = subject_id;
        this.module_id = module_id;
        this.date = date;
        this.time = time;
        this.month = month;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
