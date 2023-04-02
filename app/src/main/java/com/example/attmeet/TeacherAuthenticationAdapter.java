package com.example.attmeet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeacherAuthenticationAdapter extends RecyclerView.Adapter<TeacherAuthenticationAdapter.ViewHolder> {
    ArrayList<TeacherAuthentication_model> arrayList;
    Login contex;

    public TeacherAuthenticationAdapter(ArrayList<TeacherAuthentication_model> arrayList, Login contex) {
        this.arrayList = arrayList;
        this.contex = contex;
    }

    @NonNull
    @Override
    public TeacherAuthenticationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(contex).inflate(R.layout.activity_administration,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherAuthenticationAdapter.ViewHolder holder, int position) {
TeacherAuthentication_model model=arrayList.get(position);
holder.College_name.setText(model.getTeacherEmail());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView College_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
College_name=itemView.findViewById(R.id.Teacher_text);
        }
    }
}
