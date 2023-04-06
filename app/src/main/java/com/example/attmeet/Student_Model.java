package com.example.attmeet;

public class Student_Model {
    String StudentId,StudentName,Studentyear,StudentEmail,StudentPassword,StudentStream,StudentUid;

    public Student_Model() {
    }

    public Student_Model(String studentId, String studentName, String studentyear, String studentEmail,  String studentStream) {
        StudentId = studentId;
        StudentName = studentName;
        Studentyear = studentyear;
        StudentEmail = studentEmail;

        StudentStream = studentStream;

    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentyear() {
        return Studentyear;
    }

    public void setStudentyear(String studentyear) {
        Studentyear = studentyear;
    }

    public String getStudentEmail() {
        return StudentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        StudentEmail = studentEmail;
    }

    public String getStudentPassword() {
        return StudentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        StudentPassword = studentPassword;
    }

    public String getStudentStream() {
        return StudentStream;
    }

    public void setStudentStream(String studentStream) {
        StudentStream = studentStream;
    }

    public String getStudentUid() {
        return StudentUid;
    }

    public void setStudentUid(String studentUid) {
        StudentUid = studentUid;
    }
}
