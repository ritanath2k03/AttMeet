package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeacherDashboard extends AppCompatActivity {
DrawerLayout drawerLayout;
    androidx.appcompat.widget.Toolbar  toolbar;
NavigationView navigationView;
FirebaseAuth auth=FirebaseAuth.getInstance();
FirebaseDatabase db=FirebaseDatabase.getInstance();
DatabaseReference reference;
ImageView Viewschedule,TakeAttendance;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        toolbar=findViewById(R.id.Teachertoolbar);
        drawerLayout=findViewById(R.id.TeacherDrawerLayout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.nacigation_colse);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView=findViewById(R.id.TeacherNavigationView);
        ImageSlider slider=findViewById(R.id.imagesliderTeacher);
        List<SlideModel> list=new ArrayList<>();
        list.add(new SlideModel(R.drawable.logo_main));
        list.add(new SlideModel("https://picsum.photos/id/237/200/300"));
        list.add(new SlideModel("https://picsum.photos/200/300?grayscale"));
        list.add(new SlideModel("https://picsum.photos/id/870/200/300?grayscale&blur=2"));
        list.add(new SlideModel("https://www.pexels.com/photo/blue-orange-and-black-abstract-painting-1550562"));
        slider.setImageList(list,true);
        reference =db.getReference("Users").child(auth.getUid());
Log.d("UID",FirebaseAuth.getInstance().getUid());

TakeAttendance=findViewById(R.id.Take_Attendance);
TakeAttendance.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       Intent intent=new Intent(TeacherDashboard.this,StudentAttendance.class);

        startActivity(intent);

    }
});

Viewschedule=findViewById(R.id.ViewSchedule);

Viewschedule.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(TeacherDashboard.this,Schedule.class));

    }
});


reference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(FirebaseAuth.getInstance().getUid()!=null){
           Authentication_model model=snapshot.getValue(Authentication_model.class);
            toolbar.setTitle(model.getTeacherName());

        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

        if (savedInstanceState==null){
            navigationView.setCheckedItem(R.id.Home);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.Logout:{
                        auth.signOut();
                        startActivity(new Intent(TeacherDashboard.this,MainActivity.class));
                        Toast.makeText(TeacherDashboard.this, "Logged Out", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.Home:{
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.Home);
                        Toast.makeText(TeacherDashboard.this, "AdminPage", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.Calender:{
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.Calender);
                        Toast.makeText(TeacherDashboard.this, "Calender", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.Chat:{
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.Chat);
                        Toast.makeText(TeacherDashboard.this, "Chat", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.Massage:{
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.Massage);
                        Toast.makeText(TeacherDashboard.this, "Massage", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.Bookmark:{
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.Bookmark);
                        Toast.makeText(TeacherDashboard.this, "BookMark", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.Achivements:{
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.Achivements);
                        Toast.makeText(TeacherDashboard.this, "Avhivement", Toast.LENGTH_SHORT).show();
                        break;
                    } case R.id.Settings:{
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.Settings);
                        Toast.makeText(TeacherDashboard.this, "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                return true;
            }
        });

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}