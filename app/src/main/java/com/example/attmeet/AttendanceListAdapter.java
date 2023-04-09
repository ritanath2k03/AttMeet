package com.example.attmeet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AttendanceListAdapter extends RecyclerView.Adapter<AttendanceListAdapter.ViewHolder> {

    ArrayList<Student_Model> arrayList;
    StudentAttendance contex;

    public AttendanceListAdapter(ArrayList<Student_Model> arrayList, StudentAttendance contex) {
        this.arrayList = arrayList;
        this.contex = contex;
    }

    @NonNull
    @Override
    public AttendanceListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(contex).inflate(R.layout.presented_student,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceListAdapter.ViewHolder holder, int position) {
        Student_Model model=arrayList.get(position);
holder.presentStName.setText(model.getStudentName());
holder.pStStream.setText(model.getStudentStream());
holder.pStTimeD.setText(model.getDuration());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView presentStName,pStTimeD,pStStream;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            presentStName=itemView.findViewById(R.id.PresentStName);
            pStTimeD=itemView.findViewById(R.id.TimedurationPresent);
            pStStream=itemView.findViewById(R.id.PresentStream);
        }
    }
}
