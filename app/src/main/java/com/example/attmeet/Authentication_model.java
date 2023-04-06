package com.example.attmeet;

import java.util.Objects;

public class Authentication_model {
    String University;
    String Colleges;
    String Stream;
    String Year;
    String Roll;
    String Name;
    String Password;
    String Email;
    String CollegeId;
    String Uid;
String TeacherEmail,TeacherId,TeacherName,TeacherPassword,TeacherSubject;

    public String getTeacherEmail() {
        return TeacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        TeacherEmail = teacherEmail;
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

    public String getTeacherPassword() {
        return TeacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        TeacherPassword = teacherPassword;
    }

    public String getTeacherSubject() {
        return TeacherSubject;
    }

    public void setTeacherSubject(String teacherSubject) {
        TeacherSubject = teacherSubject;
    }

    public String getCollegeId() {
        return CollegeId;
    }

    public void setCollegeId(String collegeId) {
        CollegeId = collegeId;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public Authentication_model(String colleges) {
        Colleges = colleges;
    }

    public Authentication_model() {

    }

    public Authentication_model( String email,String password) {
        Password = password;
        this.Email = email;
    }



    public  String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUniversity() {
        return University;
    }

    public void setUniversity(String university) {
        University = university;
    }

    public String getColleges() {
        return Colleges;
    }

    public void setCollege(String college) {
        Colleges = college;
    }

    public String getStream() {
        return Stream;
    }

    public void setStream(String stream) {
        Stream = stream;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getRoll() {
        return Roll;
    }

    public void setRoll(String roll) {
        Roll = roll;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof Authentication_model)) return false;
        Authentication_model model=(Authentication_model) o;
        return Objects.equals(getEmail(),model.getEmail())&& Objects.equals(getPassword(),model.getPassword());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getEmail(),getPassword());
    }

}

