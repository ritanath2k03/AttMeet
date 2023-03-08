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

    public Authentication_adapter(ArrayList<Authentication_model> arrayList, Login context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Authentication_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.each_admin_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Authentication_adapter.ViewHolder holder, int position) {
Authentication_model model=arrayList.get(position);
holder.Password.setText(model.getPassword());
holder.Email.setText(model.getEmail());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
          TextView Password,Email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Password=itemView.findViewById(R.id.Password);
            Email=itemView.findViewById(R.id.Email);
        }
    }
}
