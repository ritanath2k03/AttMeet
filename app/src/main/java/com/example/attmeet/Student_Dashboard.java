package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Slide;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Student_Dashboard extends AppCompatActivity {
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=db.getReference("Users");
    TextView SName;
    ImageView logout;
    @SuppressLint("MissingInflatedId")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        ImageSlider slider=findViewById(R.id.Studentdash);

        List<SlideModel> list=new ArrayList<>();
        list.add(new SlideModel(R.drawable.logo_main));
        list.add(new SlideModel("https://picsum.photos/id/237/200/300"));
        list.add(new SlideModel("https://picsum.photos/200/300?grayscale"));
        list.add(new SlideModel("https://picsum.photos/id/870/200/300?grayscale&blur=2"));
        list.add(new SlideModel("https://www.pexels.com/photo/blue-orange-and-black-abstract-painting-1550562"));
        slider.setImageList(list,true);
        databaseReference=databaseReference.child(auth.getUid());
        SName=findViewById(R.id.StudentName);

        logout=findViewById(R.id.Logoutbar);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(Student_Dashboard.this,MainActivity.class));
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Authentication_model model=snapshot.getValue(Authentication_model.class);
                SName.setText(model.getStudentName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

TextView View;
View=findViewById(R.id.ClassView);
View.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(android.view.View view) {
        startActivity(new Intent(Student_Dashboard.this,StudentSchedule.class));

    }
});

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


}