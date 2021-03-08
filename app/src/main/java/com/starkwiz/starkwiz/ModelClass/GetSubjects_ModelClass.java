package com.starkwiz.starkwiz.ModelClass;

public class GetSubjects_ModelClass {
    String id,plan_id,subject_id,subject_name,subject_type;

    public GetSubjects_ModelClass(String id, String plan_id,
                                  String subject_id, String subject_name, String subject_type) {
        this.id = id;
        this.plan_id = plan_id;
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.subject_type = subject_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_type() {
        return subject_type;
    }

    public void setSubject_type(String subject_type) {
        this.subject_type = subject_type;
    }
}
