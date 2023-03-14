package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Administration extends AppCompatActivity {
ImageView Teacher_signup,Student_signup;
TextView teacher_text;
FirebaseDatabase database=FirebaseDatabase.getInstance();
DatabaseReference reference=database.getReference();
Authentication_adapter adapter;
ArrayList<Authentication_model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        androidx.appcompat.widget.Toolbar toolbar= findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        reference=database.getReference().child(FirebaseAuth.getInstance().getUid());
        list=new ArrayList<>();
        adapter=new Authentication_adapter(list,this);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Authentication_model model=dataSnapshot.getValue(Authentication_model.class);
                    if(model==null){
                        list.add(new Authentication_model("null"));
                    }else{

                        list.add(model);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        toolbar.setTitle(list.toString());



        Teacher_signup=findViewById(R.id.Teacher_Signup);
        teacher_text=findViewById(R.id.Teacher_text);

        Teacher_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


    }
}