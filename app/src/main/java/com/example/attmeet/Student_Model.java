package com.example.attmeet;

public class Student_Model {
    String StudentId,StudentName,Studentyear,StudentEmail,StudentPassword,StudentStream,StudentUid;
    String Subject,TeacherId,TeacherName;



    String Present;
    String Duration,ClassDate;

    public Student_Model(String studentName, String studentyear, String studentStream, String studentUid, String duration, String present) {
        StudentName = studentName;
        Studentyear = studentyear;
        StudentStream = studentStream;
        StudentUid = studentUid;
        Duration = duration;
        Present = present;
    }

    public String getClassDate() {
        return ClassDate;
    }

    public void setClassDate(String classDate) {
        ClassDate = classDate;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getPresent() {
        return Present;
    }

    public void setPresent(String present) {
        Present = present;
    }

    public Student_Model(String subject, String teacherId, String teacherName) {
        Subject = subject;
        TeacherId = teacherId;
        TeacherName = teacherName;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(String teacherId) {
        TeacherId = teacherId;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

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
