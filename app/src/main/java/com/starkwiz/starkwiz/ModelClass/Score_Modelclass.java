package com.starkwiz.starkwiz.ModelClass;

public class Score_Modelclass {

    String id,user_id,test_id,module_id,subject_name,total_question,total_marks,total_marks_obtained,total_points_obtained,total_gp,
            subject_id,total_time,total_acquired_time,total_correct_answer,accuracy,module_name,name,lastname,cls,district,state;

    public Score_Modelclass(String id, String user_id, String test_id, String module_id, String subject_name, String total_question, String total_marks, String total_marks_obtained, String total_points_obtained, String total_gp, String subject_id, String total_time, String total_acquired_time, String total_correct_answer, String accuracy,
                            String module_name, String name, String lastname, String cls, String district, String state) {
        this.id = id;
        this.user_id = user_id;
        this.test_id = test_id;
        this.module_id = module_id;
        this.subject_name = subject_name;
        this.total_question = total_question;
        this.total_marks = total_marks;
        this.total_marks_obtained = total_marks_obtained;
        this.total_points_obtained = total_points_obtained;
        this.total_gp = total_gp;
        this.subject_id = subject_id;
        this.total_time = total_time;
        this.total_acquired_time = total_acquired_time;
        this.total_correct_answer = total_correct_answer;
        this.accuracy = accuracy;
        this.module_name = module_name;
        this.name = name;
        this.lastname = lastname;
        this.cls = cls;
        this.district = district;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTest_id() {
        return test_id;
    }

    public void setTest_id(String test_id) {
        this.test_id = test_id;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getTotal_question() {
        return total_question;
    }

    public void setTotal_question(String total_question) {
        this.total_question = total_question;
    }

    public String getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(String total_marks) {
        this.total_marks = total_marks;
    }

    public String getTotal_marks_obtained() {
        return total_marks_obtained;
    }

    public void setTotal_marks_obtained(String total_marks_obtained) {
        this.total_marks_obtained = total_marks_obtained;
    }

    public String getTotal_points_obtained() {
        return total_points_obtained;
    }

    public void setTotal_points_obtained(String total_points_obtained) {
        this.total_points_obtained = total_points_obtained;
    }

    public String getTotal_gp() {
        return total_gp;
    }

    public void setTotal_gp(String total_gp) {
        this.total_gp = total_gp;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getTotal_time() {
        return total_time;
    }

    public void setTotal_time(String total_time) {
        this.total_time = total_time;
    }

    public String getTotal_acquired_time() {
        return total_acquired_time;
    }

    public void setTotal_acquired_time(String total_acquired_time) {
        this.total_acquired_time = total_acquired_time;
    }

    public String getTotal_correct_answer() {
        return total_correct_answer;
    }

    public void setTotal_correct_answer(String total_correct_answer) {
        this.total_correct_answer = total_correct_answer;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
