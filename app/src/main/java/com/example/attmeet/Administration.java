package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Administration extends AppCompatActivity {
ImageView Teacher_signup,Student_signup,imageView;
TextView teacher_text;
FirebaseDatabase database=FirebaseDatabase.getInstance();
DatabaseReference reference;
FirebaseAuth auth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        androidx.appcompat.widget.Toolbar toolbar= findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

//        list=new ArrayList<>();
//        adapter=new Authentication_adapter(list,this);
        Teacher_signup=findViewById(R.id.Teacher_Signup);
        teacher_text=findViewById(R.id.Teacher_text);
        imageView=findViewById(R.id.imageView2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(Administration.this,MainActivity.class));
            }
        });
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

                    Log.d("UNI",model.getUniversity());

                    Log.d("UNI",model.getName());

                    Teacher_signup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Administration.this,Enrollment_Teacher_Student.class));

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}