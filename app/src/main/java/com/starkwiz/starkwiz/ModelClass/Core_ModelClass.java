package com.starkwiz.starkwiz.ModelClass;

public class Core_ModelClass {

    String SubjectId,Subjectname,Subjecttype;

    public Core_ModelClass(String subjectId, String subjectname, String subjecttype) {
        SubjectId = subjectId;
        Subjectname = subjectname;
        Subjecttype = subjecttype;
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

    public String getSubjecttype() {
        return Subjecttype;
    }

    public void setSubjecttype(String subjecttype) {
        Subjecttype = subjecttype;
    }

    @Override
    public String toString() {
        return "{" +
                "SubjectId='" + SubjectId + '\'' +
                ", Subjectname='" + Subjectname + '\'' +
                ", Subjecttype='" + Subjecttype + '\'' +
                '}';
    }
}
