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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AttendanceCheck extends AppCompatActivity {
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference reference=db.getReference("Users");
    Button button,exit,makeRequest;
    String m_start,m_end;
    String St_name,St_stream,St_Year,St_Uid;
    TextView tname,tsub;

    String subject,teacherId,teacherName;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_check);
        button=findViewById(R.id.JoinBtn);
        subject=getIntent().getStringExtra("SubjectName");
        teacherId=getIntent().getStringExtra("TeacherIdformeet");
        teacherName=getIntent().getStringExtra("TeacherNameformeet");
        m_end="0.0";
        m_start="0.0";
        tname=findViewById(R.id.teachername);
        tsub=findViewById(R.id.Subjectname);
        tname.setText(teacherName);
        tsub.setText(subject);

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
        final long[] diff = new long[1];
        reference.child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student_Model model=snapshot.getValue(Student_Model.class);
                St_name=model.getStudentName();
                St_stream=model.getStudentStream();
                St_Uid=model.getStudentUid();
                St_Year=model.getStudentyear();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date=new Date();
                SimpleDateFormat formatdate=new SimpleDateFormat("dd-MM-YYYY");

                String dateString=formatdate.format(date);

                reference.child(auth.getUid()).child("Attendance").child(subject).child(dateString).child("Time").child("TerminationTime").setValue(m_end);
                SimpleDateFormat sdf=new SimpleDateFormat("hh:mm");
                try {
                    Date d1=sdf.parse(m_start);
                    Date d2=sdf.parse(m_end);
                    diff[0] =d2.getTime()-d1.getTime();
                    String duration= String.valueOf((diff[0] /(1000*60)));
                    HashMap<String, Object> map=new HashMap<>();
                    map.put("StudentName",St_name);
                    map.put("StudentUid",St_Uid);
                    map.put("StudentStream",St_stream+St_Year);
                    map.put("Present","False");
                    map.put("Duration",duration);
                    map.put("Subject",subject);

                    Date date1=new Date();
                    SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("dd-MM-YYYY");
                    String DateString=simpleDateFormat1.format(date1);
                    map.put("ClassDate",DateString);
                    reference.child(auth.getUid()).child("Attendance").child(subject).child(dateString).child("Time").child("Duration").setValue((diff[0] /(1000*60))).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            reference.child(teacherId).child("StudentAttendance").child(St_stream+St_Year).child(DateString).child(St_Uid).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(AttendanceCheck.this, "Requested for Attendance", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    });
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }


            }
        });




//makeRequest.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//
//
//    }
//});



    }

    private void onTerminate(String format) {
        Toast.makeText(this, format, Toast.LENGTH_SHORT).show();
        m_end=format;

    }



    @Override
    protected void onRestart() {
        super.onRestart();
        Date date1=new Date();
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("dd-MM-YYYY");

        reference.child(auth.getUid()).child("Attendance").child(subject).child(simpleDateFormat1.format(date1)).child("Time").child("JoinTime").setValue(m_start).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });




    }
    public void onsend( String Start_time) {
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
                onTerminate(formatdate.format(date));
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver1,intentFilter1);
    }
}