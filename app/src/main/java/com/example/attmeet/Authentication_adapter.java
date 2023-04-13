package com.example.attmeet;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.Context;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.util.ArrayList;

public class Authentication_adapter extends RecyclerView.Adapter<Authentication_adapter.ViewHolder> {
    ArrayList<Authentication_model> arrayList;
    Login context;
Schedule contextSchedule;
FirebaseAuth auth=FirebaseAuth.getInstance();

    public Authentication_adapter(ArrayList<Authentication_model> arrayList, Schedule contextSchedule) {
        this.arrayList = arrayList;
        this.contextSchedule = contextSchedule;
    }

    Administration administrationContext;

    public Authentication_adapter(ArrayList<Authentication_model> arrayList, Login context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Authentication_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(contextSchedule).inflate(R.layout.student_each_day,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Authentication_adapter.ViewHolder holder, int position) {
Authentication_model model=arrayList.get(position);
holder.Stream.setText(model.getStream());
holder.Subject.setText(model.getSubject());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//              Intent intent= new Intent(contextSchedule, TeacherMeetJoin.class);
//                intent.putExtra("SubjectName",model.getSubject());
//              intent.putExtra("TeacherIdformeet",model.getTeacherId());
//                contextSchedule.startActivity(intent);


                meet(model.getTeacherId());
            }
        });
    }

    private void meet(String teacherId) {
        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                .setRoom(auth.getUid())
                .build();
        JitsiMeetActivity.launch(contextSchedule,options);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
          TextView Stream,Subject;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Stream=itemView.findViewById(R.id.Teachereachday);
            Subject=itemView.findViewById(R.id.sub);

        }
    }
}
