package com.starkwiz.starkwiz.ModelClass;

public class Login_ModelClass {

    String access_token,id,first_name,last_name,mobile_number,cls,school_board,role,email;

    public Login_ModelClass(String access_token, String id, String first_name, String last_name,
                            String mobile_number, String cls, String school_board, String role, String email) {
        this.access_token = access_token;
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobile_number = mobile_number;
        this.cls = cls;
        this.school_board = school_board;
        this.role = role;
        this.email = email;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getSchool_board() {
        return school_board;
    }

    public void setSchool_board(String school_board) {
        this.school_board = school_board;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
