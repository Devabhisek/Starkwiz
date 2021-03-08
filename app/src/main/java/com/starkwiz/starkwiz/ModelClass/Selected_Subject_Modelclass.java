package com.starkwiz.starkwiz.ModelClass;

public class Selected_Subject_Modelclass {
    String subject_id,subject_name,subject_type,plan_id,user_id;

    public Selected_Subject_Modelclass(String subject_id, String subject_name, String subject_type, String plan_id, String user_id) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.subject_type = subject_type;
        this.plan_id = plan_id;
        this.user_id = user_id;
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

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "{" +
                "subject_id='" + subject_id + '\'' +
                ", subject_name='" + subject_name + '\'' +
                ", subject_type='" + subject_type + '\'' +
                ", plan_id='" + plan_id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
