package com.example.attmeet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Enrollment_Teacher_Student extends AppCompatActivity {
TextInputEditText Name,Id,Subject,Email,Password;
TextView submit,Cancel;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_teacher_student);
        Name=findViewById(R.id.Teacher_Name_signup);
        Id=findViewById(R.id.Teacher_Id_signup);
        Subject=findViewById(R.id.Teacher_Subject);
        Email=findViewById(R.id.Email_Teacher_signin);
        Password=findViewById(R.id.Password_Teacher_signin);
        Cancel=findViewById(R.id.Cancel_button);
        submit=findViewById(R.id.SubmitTextView);

    }
}