package com.example.attmeet;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jitsi.meet.sdk.BroadcastEvent;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentSchedule extends AppCompatActivity {

    FirebaseDatabase db=FirebaseDatabase.getInstance();
    FirebaseAuth auth=FirebaseAuth.getInstance();
    DatabaseReference reference=db.getReference("Users");
    TextView headername;
    StudentScheduelAdapter adapter_m,adapter_t,adapter_w,adapter_th,adapter_f;
    RecyclerView recyclerView_m,recyclerView_t,recyclerView_w,recyclerView_th,recyclerView_f;
String m_start="0.0",m_End="0.0";
    ArrayList<Student_Model> arrayList_m,arrayList_t,arrayList_w,arrayList_th,arrayList_f;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule);
        recyclerView_m=findViewById(R.id.Monday);
        recyclerView_t=findViewById(R.id.Tuesday);
        recyclerView_w=findViewById(R.id.Wednesday);
        recyclerView_th=findViewById(R.id.Thursday);
        recyclerView_f=findViewById(R.id.Friday);
        headername=findViewById(R.id.header_name);



m_start="0.0";
m_End="0.0";




        reference.child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student_Model model=snapshot.getValue(Student_Model.class);
                headername.setText(model.getStudentStream()+model.getStudentyear());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






//for monday schedule

       arrayList_m=new ArrayList<>();
       adapter_m=new StudentScheduelAdapter(arrayList_m,this);
        arrayList_t=new ArrayList<>();
        adapter_t=new StudentScheduelAdapter(arrayList_t,this);
        recyclerView_m.setHasFixedSize(true);
        recyclerView_m.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
       recyclerView_m.setAdapter(adapter_m);

           reference.child(auth.getUid()).child("Schedule").child("Monday").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            arrayList_m.clear();
            for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                Student_Model model=dataSnapshot.getValue(Student_Model.class);
                arrayList_m.add(model);
            }
            adapter_m.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

           //for tuesday
        arrayList_t=new ArrayList<>();
        adapter_t=new StudentScheduelAdapter(arrayList_t,this);
        recyclerView_t.setHasFixedSize(true);
        recyclerView_t.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView_t.setAdapter(adapter_t);
    reference.child(auth.getUid()).child("Schedule").child("Tuesday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList_t.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Student_Model model=dataSnapshot.getValue(Student_Model.class);
                    arrayList_t.add(model);

                }
                adapter_t.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    //for wednesday
        arrayList_w=new ArrayList<>();
        adapter_w=new StudentScheduelAdapter(arrayList_w,this);
        recyclerView_w.setHasFixedSize(true);
        recyclerView_w.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView_w.setAdapter(adapter_w);
        reference.child(auth.getUid()).child("Schedule").child("Wednesday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList_w.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Student_Model model=dataSnapshot.getValue(Student_Model.class);
                    arrayList_w.add(model);
                }
                adapter_w.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //for Thursday

        arrayList_th=new ArrayList<>();
        adapter_th=new StudentScheduelAdapter(arrayList_th,this);
        recyclerView_th.setHasFixedSize(true);
        recyclerView_th.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView_th.setAdapter(adapter_th);
        reference.child(auth.getUid()).child("Schedule").child("Thursday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList_th.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Student_Model model=dataSnapshot.getValue(Student_Model.class);
                    arrayList_th.add(model);
                }
                adapter_th.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //for friday
        arrayList_f=new ArrayList<>();
        adapter_f=new StudentScheduelAdapter(arrayList_f,this);
        recyclerView_f.setHasFixedSize(true);
        recyclerView_f.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView_f.setAdapter(adapter_f);
        reference.child(auth.getUid()).child("Schedule").child("Friday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList_f.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Student_Model model=dataSnapshot.getValue(Student_Model.class);
                    arrayList_f.add(model);
                }
                adapter_f.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Date date;
        SimpleDateFormat formatdate;

String subject,m_end;
AttendanceCheck attendanceCheck=new AttendanceCheck();
subject=attendanceCheck.subject;
m_end=attendanceCheck.m_end;
        date=new Date();
        formatdate=new SimpleDateFormat("hh:mm:ss  DD/MM/YYYY");





    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JitsiMeetActivity jitsiMeetActivity=new JitsiMeetActivity();


    }

    @Override
    protected void onStop() {
        super.onStop();

    }



}