package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

public class Signin extends AppCompatActivity  implements PopupMenu.OnMenuItemClickListener {
TextView menu_dropdown;
NavigationView navigationView;
EditText Email,Stream,Name,DOB,College_name,Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        menu_dropdown=findViewById(R.id.Menuoption_textViewbtn);
//        navigationView=findViewById(R.id.Top_Nav_Bar_Navigation_View);
        Email=findViewById(R.id.Email_signin);
        Stream=findViewById(R.id.Stream_signin);
        Name=findViewById(R.id.Name_signin);
        DOB=findViewById(R.id.Dob_signin);
        College_name=findViewById(R.id.College_signin);
        Id=findViewById(R.id.Id_signin);
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
                break;
            case R.id.Teacher:

                menu_dropdown.setText("Teacher");
                Log.d("menu","working");
                Name.setVisibility(View.VISIBLE);
                DOB.setHint("Enter DOB");
                Stream.setHint("Enter Subject");
                break;
            case R.id.Student:

                menu_dropdown.setText("Student");
                Log.d("menu","working");
                Name.setVisibility(View.VISIBLE);
                DOB.setHint("Enter DOB");
                Stream.setHint("Enter Stream");
                break;
        }
        return false;
    }


//    @Override
//    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.signin_menu, menu);
//
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        menu_dropdown=findViewById(R.id.Menuoption_textViewbtn);
//        switch (item.getItemId()){
//            case R.id.Administration:
//
//                menu_dropdown.setText("Administration");
//                Log.d("menu","working");
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}