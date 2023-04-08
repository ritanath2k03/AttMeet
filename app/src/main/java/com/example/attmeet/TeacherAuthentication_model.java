package com.example.attmeet;

import java.util.Objects;

public class TeacherAuthentication_model {
    String TeacherEmail,TeacherPassword;



    public TeacherAuthentication_model() {
    }

    public TeacherAuthentication_model(String teacherEmail, String teacherPassword) {
        this.TeacherEmail = teacherEmail;
        TeacherPassword = teacherPassword;
    }

    public String getTeacherEmail() {
        return TeacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        TeacherEmail = teacherEmail;
    }

    public String getTeacherPassword() {
        return TeacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        TeacherPassword = teacherPassword;
    }
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof TeacherAuthentication_model)) return false;
        TeacherAuthentication_model model= (TeacherAuthentication_model)  o;
        return Objects.equals(getTeacherEmail(),model.getTeacherEmail())&&Objects.equals(getTeacherPassword(),model.getTeacherPassword());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getTeacherEmail(),getTeacherPassword());
    }
}
