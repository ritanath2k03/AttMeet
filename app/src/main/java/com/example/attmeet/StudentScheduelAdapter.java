package com.example.attmeet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jitsi.meet.sdk.BroadcastEvent;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.JitsiMeetUserInfo;
import org.jitsi.meet.sdk.JitsiMeetView;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class StudentScheduelAdapter extends RecyclerView.Adapter<StudentScheduelAdapter.ViewHoldar> {
    ArrayList<Student_Model> arrayList;
FirebaseAuth auth=FirebaseAuth.getInstance();
FirebaseDatabase db=FirebaseDatabase.getInstance();
Date  date=new Date();

    StudentSchedule context;

    public StudentScheduelAdapter(ArrayList<Student_Model> arrayList, StudentSchedule context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public StudentScheduelAdapter.ViewHoldar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.student_each_day,parent,false);
        return new ViewHoldar(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentScheduelAdapter.ViewHoldar holder, int position) {
Student_Model model=arrayList.get(position);
holder.Teacher.setText(model.getTeacherName());
holder.Subject.setText(model.getSubject());



holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent intent=new Intent(context,AttendanceCheck.class);
        intent.putExtra("SubjectName",model.getSubject());
        intent.putExtra("TeacherIdformeet",model.getTeacherId());
        intent.putExtra("TeacherNameformeet",model.getTeacherName());
        context.startActivity(intent);

    }
});



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHoldar extends RecyclerView.ViewHolder {
TextView Subject,Teacher,TeacherId;
        public ViewHoldar(@NonNull View itemView) {
            super(itemView);
            Subject=itemView.findViewById(R.id.sub);
            Teacher=itemView.findViewById(R.id.Teachereachday);



        }
    }



}

