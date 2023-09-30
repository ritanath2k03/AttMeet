package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class StudentAttendance extends AppCompatActivity {
EditText takeStream;
Button takeattendance,viewAttendance, absent;
TextView takeDate;
RecyclerView s_list;
ArrayList<Student_Model > arrayList,arrayList1,arrayList2;
StudentAttendanceAdapter adapter;
AttendanceListAdapter adapter1;
    AttendanceListAdapter adapter2;

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

absent =findViewById(R.id.ViewAbsenTAttendance);

takeDate.setOnClickListener(v->{pickDate();});
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
        absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arrayList1=new ArrayList<>();
                adapter1=new AttendanceListAdapter(arrayList1,StudentAttendance.this);
                s_list.setLayoutManager(new LinearLayoutManager(StudentAttendance.this,LinearLayoutManager.VERTICAL,false));
                s_list.setHasFixedSize(true);
                s_list.setAdapter(adapter1);

                if(takeDate.getText().toString()!=null&&takeStream.getText().toString()!=null) {

                    reference.child(auth.getUid()).child("StudentAttendance").child(takeStream.getText().toString().toUpperCase()).child(takeDate.getText().toString()).child("Absent").addValueEventListener(new ValueEventListener() {
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

    private void pickDate() {
        final Calendar c=Calendar.getInstance();
        int y=c.get(Calendar.YEAR);
        int m=c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                // on below line we are passing context.
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // on below line we are setting date to our text view.

                        if(dayOfMonth<10)
                            takeDate.setText("0"+dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                         if((monthOfYear + 1)<10)
                            takeDate.setText(dayOfMonth + "-" +"0"+(monthOfYear + 1) + "-" + year);
                         if((monthOfYear + 1)<10&&dayOfMonth<10)
                            takeDate.setText("0"+dayOfMonth + "-" +"0"+(monthOfYear + 1) + "-" + year);
                        if(dayOfMonth>=10&&monthOfYear>=10) takeDate.setText(dayOfMonth + "-"+(monthOfYear + 1) + "-" + year);
                    }
                },
                // on below line we are passing year,
                // month and day for selected date in our date picker.
                y, m, d);
        // at last we are calling show to
        // display our date picker dialog.
        datePickerDialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onBackPressed() {

            super.onBackPressed();
            Intent intent=new Intent(StudentAttendance.this,TeacherDashboard.class);
            startActivity(intent);

    }
}