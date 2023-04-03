package com.example.attmeet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;

public class Schedule extends AppCompatActivity {
Toolbar toolbar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        toolbar=findViewById(R.id.Toolbar);
setSupportActionBar(toolbar);
        toolbar.setTitle("Schedule");

    }
}