package com.starkwiz.starkwiz.ModelClass;

public class GetTestList_ModelClass {

    String module_id,subject_id,test_id,module_number,module_name,hour,minutes,no_of_questions;

    public GetTestList_ModelClass(String module_id, String subject_id, String test_id, String module_number,
                                  String module_name, String hour, String minutes, String datetime, String no_of_questions) {
        this.module_id = module_id;
        this.subject_id = subject_id;
        this.test_id = test_id;
        this.module_number = module_number;
        this.module_name = module_name;
        this.hour = hour;
        this.minutes = minutes;
        this.no_of_questions = no_of_questions;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getTest_id() {
        return test_id;
    }

    public void setTest_id(String test_id) {
        this.test_id = test_id;
    }

    public String getModule_number() {
        return module_number;
    }

    public void setModule_number(String module_number) {
        this.module_number = module_number;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }



    public String getNo_of_questions() {
        return no_of_questions;
    }

    public void setNo_of_questions(String no_of_questions) {
        this.no_of_questions = no_of_questions;
    }
}
