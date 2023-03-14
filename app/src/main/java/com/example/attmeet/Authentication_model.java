package com.example.attmeet;

import java.util.Objects;

public class Authentication_model {
    String University;
    String College;
    String Stream;
    String Year;
    String Roll;
    String Name;
    String Password;
    String Email;

    public Authentication_model(String college) {
        College = college;
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

    public String getCollege() {
        return College;
    }

    public void setCollege(String college) {
        College = college;
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
