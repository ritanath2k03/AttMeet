package com.example.attmeet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;

public class StudentAttendanceAdapter extends RecyclerView.Adapter<StudentAttendanceAdapter.ViewHoldar> {

    ArrayList<Student_Model> arrayList;

    StudentAttendance contex;
FirebaseAuth auth=FirebaseAuth.getInstance();
FirebaseDatabase db=FirebaseDatabase.getInstance();
DatabaseReference reference=db.getReference("Users");
    public StudentAttendanceAdapter(ArrayList<Student_Model> arrayList, StudentAttendance contex) {
        this.arrayList = arrayList;
        this.contex = contex;
    }

    @NonNull
    @Override
    public StudentAttendanceAdapter.ViewHoldar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(contex).inflate(R.layout.student_timeduration,parent,false);

        return new ViewHoldar(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAttendanceAdapter.ViewHoldar holder, int position) {
Student_Model model=arrayList.get(position);
holder.eStudentView.setText(model.getStudentName());
holder.Timeduration.setText(model.getDuration());

holder.MakePresent.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("Present","Present");
        reference.child(model.getStudentUid()).child("Attendance").child(model.getSubject()).child(model.getClassDate()).child("Time")
                .setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        HashMap<String,Object> map1=new HashMap<>();
                        map1.put("StudentName",model.getStudentName());
                        map1.put("StudentUid",model.getStudentUid());
                        map1.put("StudentYear",model.getStudentyear());
                        map1.put("StudentStream",model.getStudentStream());
                        map1.put("Present","Present");
                        map1.put("Duration",model.getDuration());
                        map1.put("Subject",model.getSubject());
                        String path=model.getStudentStream();
                        reference.child(auth.getUid()).child("StudentAttendance").child(path).child(model.getClassDate()).child("Present").child(model.getStudentUid())
                                .setValue(map1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        holder.MakeAbsent.invalidate();
                                        Toast.makeText(contex, "Present", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });



    }
});

        holder.MakeAbsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> map=new HashMap<>();
                map.put("Present","Absent");
                reference.child(model.getStudentUid()).child("Attendance").child(model.getSubject()).child(model.getClassDate()).child("Time")
                        .setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(contex,"Absent", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });


    }


    @Override
    public int getItemCount() {
        return arrayList.size() ;
    }


    public class ViewHoldar extends RecyclerView.ViewHolder {
        TextView eStudentView,Timeduration;
        ImageView MakePresent,MakeAbsent;

        public ViewHoldar(@NonNull View itemView) {
            super(itemView);
            eStudentView=itemView.findViewById(R.id.eStudentView);
            Timeduration=itemView.findViewById(R.id.Timeduration);
            MakeAbsent=itemView.findViewById(R.id.MakeAbsent);
            MakePresent=itemView.findViewById(R.id.MakePresent);
        }
    }
}
