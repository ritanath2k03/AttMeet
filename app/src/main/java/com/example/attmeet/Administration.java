package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.transition.Slide;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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

public class Administration extends AppCompatActivity {
ImageView Teacher_signup,Student_signup,Schedule_admin;

TextView teacher_text;
FirebaseDatabase database=FirebaseDatabase.getInstance();
DatabaseReference reference;
FirebaseAuth auth=FirebaseAuth.getInstance();
DrawerLayout drawerLayout;
NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        androidx.appcompat.widget.Toolbar toolbar= findViewById(R.id.toolbar);

drawerLayout=findViewById(R.id.drawerlayout);
navigationView=findViewById(R.id.NavigationView);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.nacigation_colse);
drawerLayout.addDrawerListener(toggle);
toggle.syncState();
        //setSupportActionBar(toolbar);

        //Image slider
        ImageSlider slider=findViewById(R.id.imageslider);
        List<SlideModel> list=new ArrayList<>();
        list.add(new SlideModel(R.drawable.logo_main));
        list.add(new SlideModel("https://picsum.photos/id/237/200/300"));
        list.add(new SlideModel("https://picsum.photos/200/300?grayscale"));
        list.add(new SlideModel("https://picsum.photos/id/870/200/300?grayscale&blur=2"));
        list.add(new SlideModel("https://www.pexels.com/photo/blue-orange-and-black-abstract-painting-1550562"));
        slider.setImageList(list,true);
//Accessing sidebar menu item

        if (savedInstanceState==null){
            navigationView.setCheckedItem(R.id.Home);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.Logout:{
                        auth.signOut();
                        startActivity(new Intent(Administration.this,MainActivity.class));
                        Toast.makeText(Administration.this, "Logged Out", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.Home:{
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.Home);
                        Toast.makeText(Administration.this, "AdminPage", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.Calender:{
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.Calender);
                        Toast.makeText(Administration.this, "Calender", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.Chat:{
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.Chat);
                        Toast.makeText(Administration.this, "Chat", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.Massage:{
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.Massage);
                        Toast.makeText(Administration.this, "Massage", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.Bookmark:{
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.Bookmark);
                        Toast.makeText(Administration.this, "BookMark", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.Achivements:{
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.Achivements);
                        Toast.makeText(Administration.this, "Avhivement", Toast.LENGTH_SHORT).show();
                        break;
                    } case R.id.Settings:{
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.Settings);
                        Toast.makeText(Administration.this, "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                return true;
            }
        });

        Teacher_signup=findViewById(R.id.Teacher_Signup);
        teacher_text=findViewById(R.id.Teacher_text);
        Schedule_admin=findViewById(R.id.Schedule_admin);
        Student_signup=findViewById(R.id.Student_signup);

        reference=database.getReference("Users").child(auth.getUid());
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                list.clear();

                if(FirebaseAuth.getInstance().getUid()!=null){
                    Authentication_model model1=snapshot.getValue(Authentication_model.class);
                    toolbar.setTitle(model1.getName());

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




       // Toast.makeText(this, reference.toString(), Toast.LENGTH_SHORT).show();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(FirebaseAuth.getInstance().getUid()!=null){
                    Authentication_model model=snapshot.getValue(Authentication_model.class);

                Teacher_signup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Administration.this,Enrollment_Teacher_Student.class));

                        }
                    });
                    Student_signup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Administration.this,StudentEnroll.class));
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
ArrayList<String> arrayList = new ArrayList<>();
        reference.child("Teachers").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.add("fsadfdsaf");
                arrayList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    arrayList.add(dataSnapshot.getKey());
                }
                Toast.makeText(Administration.this, arrayList.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Schedule_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Administration.this,Schedule.class));
            }
        });


    }
}