package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

public class Signin extends AppCompatActivity  implements PopupMenu.OnMenuItemClickListener {
TextView menu_dropdown,cancel,submit;
NavigationView navigationView;
EditText Email,Stream,Name,DOB,College_name,Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        menu_dropdown=findViewById(R.id.Menuoption_textViewbtn);

        Email=findViewById(R.id.Email_signin);
        Stream=findViewById(R.id.Stream_signin);
        Name=findViewById(R.id.Name_signin);
        DOB=findViewById(R.id.Dob_signin);
        College_name=findViewById(R.id.College_signin);
        Id=findViewById(R.id.Id_signin);
        cancel=findViewById(R.id.Cancel_button);
        submit=findViewById(R.id.Submit_button);
        //For Dromdown menu
menu_dropdown.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Showpopup(view);
    }
});



    }

    private void Showpopup(View v) {
        PopupMenu popupMenu=new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) this);
        popupMenu.inflate(R.menu.signin_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.Administration:

                menu_dropdown.setText("Administration");
                Log.d("menu","working");
               Name.setVisibility(View.INVISIBLE);
               DOB.setHint("Date From");
               Stream.setHint("University");
               getAllData(1);
                break;
            case R.id.Teacher:

                menu_dropdown.setText("Teacher");
                Log.d("menu","working");
                Name.setVisibility(View.VISIBLE);
                DOB.setHint("Enter DOB");
                Stream.setHint("Enter Subject");
                getAllData(2);
                break;
            case R.id.Student:

                menu_dropdown.setText("Student");
                Log.d("menu","working");
                Name.setVisibility(View.VISIBLE);
                DOB.setHint("Enter DOB");
                Stream.setHint("Enter Stream");
                getAllData(3);
                break;
        }
        return false;
    }

    private void getAllData(int i) {
        if(i==1){
           String Got_DateFrom= DOB.getText().toString();
            String Got_CollegeName= College_name.getText().toString();
            String Got_CollegeId= Id.getText().toString();
            String Got_University= Stream.getText().toString();
            String Got_Email= Email.getText().toString();


        }
        else if(i==2){
            String Got_TeacherName= Name.getText().toString();
            String Got_TeacherDOB= DOB.getText().toString();
            String Got_CollegeName= College_name.getText().toString();
            String Got_TeacherId= Id.getText().toString();
            String Got_Subject= Stream.getText().toString();
            String Got_TeacherEmail= Email.getText().toString();
        }
        else if(i==3){
            String Got_StudentName= Name.getText().toString();
            String Got_StudentDOB= DOB.getText().toString();
            String Got_CollegeName= College_name.getText().toString();
            String Got_StudentId= Id.getText().toString();
            String Got_StudentStream= Stream.getText().toString();
            String Got_TeacherEmail= Email.getText().toString();
        }


    }


}