package com.starkwiz.starkwiz.ModelClass;

public class Extra_Subjectplan_ModelClass {
    String id,plan_id,subject_name,subject_type,plan_type;

    public Extra_Subjectplan_ModelClass(String id, String plan_id,
                                           String subject_name, String subject_type, String plan_type) {
        this.id = id;
        this.plan_id = plan_id;
        this.subject_name = subject_name;
        this.subject_type = subject_type;
        this.plan_type = plan_type;
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

    public String getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(String plan_type) {
        this.plan_type = plan_type;
    }
}
