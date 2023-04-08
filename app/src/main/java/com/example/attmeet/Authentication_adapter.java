package com.example.attmeet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;

import java.util.ArrayList;

public class Authentication_adapter extends RecyclerView.Adapter<Authentication_adapter.ViewHolder> {
    ArrayList<Authentication_model> arrayList;
    Login context;
Administration context1;
    public Authentication_adapter(ArrayList<Authentication_model> arrayList, Administration administrationContext) {
        this.arrayList = arrayList;
        this.administrationContext = administrationContext;
    }

    Administration administrationContext;

    public Authentication_adapter(ArrayList<Authentication_model> arrayList, Login context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Authentication_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context1).inflate(R.layout.activity_administration,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Authentication_adapter.ViewHolder holder, int position) {
Authentication_model model=arrayList.get(position);
holder.College_name.setText(model.getName());
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
