package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jitsi.meet.sdk.BroadcastEvent;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AttendanceCheck extends AppCompatActivity {
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference reference=db.getReference("Users");
    Button button,exit;
    String m_start,m_end;

    String subject,teacherId;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_check);
        button=findViewById(R.id.JoinBtn);
        subject=getIntent().getStringExtra("SubjectName");
        teacherId=getIntent().getStringExtra("TeacherIdformeet");
        m_end="0.0";
        m_start="0.0";
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm:ss dd/MM/YYYY");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(teacherId)
                        .build();
                JitsiMeetActivity.launch(AttendanceCheck.this,options);


                IntentFilter intentFilter=new IntentFilter();
                intentFilter.addAction(BroadcastEvent.Type.CONFERENCE_JOINED.getAction());
                BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {

                    @Override
                    public void onReceive(Context context, Intent intent) {

////                 studentSchedule.onsend(sub,simpleDateFormat.format(date));
//                    Toast.makeText(context, sub+simpleDateFormat.format(date), Toast.LENGTH_SHORT).show();

                        onsend(simpleDateFormat.format(date));

                    }
                };
                LocalBroadcastManager.getInstance(AttendanceCheck.this).registerReceiver(broadcastReceiver,intentFilter);

            }
        });
        IntentFilter intentFilter1=new IntentFilter();
        intentFilter1.addAction(BroadcastEvent.Type.CONFERENCE_TERMINATED.getAction());
        BroadcastReceiver broadcastReceiver1=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Date date1=new Date();
                SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("hh:mm:ss   dd/MM/YYYY");
                Toast.makeText(AttendanceCheck.this, "Terminated", Toast.LENGTH_SHORT).show();
                onTerminate(simpleDateFormat1.format(date1));

            }
        };
        LocalBroadcastManager.getInstance(AttendanceCheck.this).registerReceiver(broadcastReceiver1,intentFilter1);
        exit=findViewById(R.id.exitBtn);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child(auth.getUid()).child("Attendance").child(subject).child("TerminationTime").setValue(m_end);

            }
        });



    }

    private void onTerminate(String format) {
        Toast.makeText(this, format, Toast.LENGTH_SHORT).show();
        m_end=format;

    }



    @Override
    protected void onRestart() {
        super.onRestart();
        Date date;
       SimpleDateFormat formatdate;

        reference.child(auth.getUid()).child("Attendance").child(subject).child("JoinTime").setValue(m_start).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });




    }
    public void onsend( String Start_time) {
        Toast.makeText(this, Start_time, Toast.LENGTH_SHORT).show();
        m_start=Start_time;
    }

    @Override
    protected void onDestroy() {
        Date date;
        SimpleDateFormat formatdate;
        super.onDestroy();
        date=new Date();
        formatdate=new SimpleDateFormat("hh:mm:ss  DD/MM/YYYY");
        IntentFilter intentFilter1=new IntentFilter();
        intentFilter1.addAction(BroadcastEvent.Type.CONFERENCE_TERMINATED.getAction());
        BroadcastReceiver broadcastReceiver1=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(AttendanceCheck.this, "Terminated", Toast.LENGTH_SHORT).show();
                onTerminate(formatdate.format(date));
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver1,intentFilter1);
    }
}