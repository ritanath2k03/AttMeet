package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentAttendance extends AppCompatActivity {
EditText takeStream;
Button takeattendance,viewAttendance;
EditText takeDate;
RecyclerView s_list;
ArrayList<Student_Model > arrayList,arrayList1;
StudentAttendanceAdapter adapter;
AttendanceListAdapter adapter1;

FirebaseDatabase db=FirebaseDatabase.getInstance();
FirebaseAuth auth=FirebaseAuth.getInstance();
DatabaseReference reference=db.getReference("Users");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);

        takeStream=findViewById(R.id.StreamPicker);
        takeattendance=findViewById(R.id.takeAttendance);
        takeDate=findViewById(R.id.datePicker);
        s_list=findViewById(R.id.sList);
viewAttendance=findViewById(R.id.ViewAttendance);




        takeattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arrayList=new ArrayList<>();
                adapter=new StudentAttendanceAdapter(arrayList,StudentAttendance.this);
                s_list.setLayoutManager(new LinearLayoutManager(StudentAttendance.this,LinearLayoutManager.VERTICAL,false));
                s_list.setHasFixedSize(true);
                s_list.setAdapter(adapter);
                if(takeDate.getText().toString()!=null&&takeStream.getText().toString()!=null) {
                    reference.child(auth.getUid()).child("StudentAttendance").child(takeStream.getText().toString().toUpperCase()).child(takeDate.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            arrayList.clear();
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Student_Model model = dataSnapshot.getValue(Student_Model.class);
                                 if(model.getStudentName()!=null)
                                arrayList.add(model);
                            }
                            adapter.notifyDataSetChanged();


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });


         viewAttendance.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 arrayList1=new ArrayList<>();
                 adapter1=new AttendanceListAdapter(arrayList1,StudentAttendance.this);
                 s_list.setLayoutManager(new LinearLayoutManager(StudentAttendance.this,LinearLayoutManager.VERTICAL,false));
                 s_list.setHasFixedSize(true);
                 s_list.setAdapter(adapter1);

                 if(takeDate.getText().toString()!=null&&takeStream.getText().toString()!=null) {

                     reference.child(auth.getUid()).child("StudentAttendance").child(takeStream.getText().toString().toUpperCase()).child(takeDate.getText().toString()).child("Present").addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot snapshot) {
                             arrayList1.clear();
                             for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                 Student_Model model = dataSnapshot.getValue(Student_Model.class);

                                 arrayList1.add(model);
                             }
                             adapter1.notifyDataSetChanged();


                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError error) {

                         }
                     });

                 }
             }
         });
    }
}