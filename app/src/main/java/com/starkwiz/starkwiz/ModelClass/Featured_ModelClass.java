package com.starkwiz.starkwiz.ModelClass;

public class Featured_ModelClass {
    String SubjectId,Subjectname,SubjectType;

    public Featured_ModelClass(String subjectId, String subjectname, String subjectType) {
        SubjectId = subjectId;
        Subjectname = subjectname;
        SubjectType = subjectType;
    }

    public String getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(String subjectId) {
        SubjectId = subjectId;
    }

    public String getSubjectname() {
        return Subjectname;
    }

    public void setSubjectname(String subjectname) {
        Subjectname = subjectname;
    }

    public String getSubjectType() {
        return SubjectType;
    }

    public void setSubjectType(String subjectType) {
        SubjectType = subjectType;
    }

    @Override
    public String toString() {
        return "{" +
                "SubjectId='" + SubjectId + '\'' +
                ", Subjectname='" + Subjectname + '\'' +
                ", SubjectType='" + SubjectType + '\'' +
                '}';
    }
}
