package com.starkwiz.starkwiz.ModelClass;

public class Core_ModelClass {

    String Id,Subjectname;

    public Core_ModelClass(String id, String subjectname) {
        Id = id;
        Subjectname = subjectname;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSubjectname() {
        return Subjectname;
    }

    public void setSubjectname(String subjectname) {
        Subjectname = subjectname;
    }

    @Override
    public String toString() {
        return "{" +
                "Id='" + Id + '\'' +
                ", Subjectname='" + Subjectname + '\'' +
                '}';
    }
}
