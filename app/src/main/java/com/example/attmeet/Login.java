package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Login extends AppCompatActivity {
    TextView Select,Display,admin,teacher,student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Select=findViewById(R.id.Menuoption_textViewbtn);

Select.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        showDialog();
    }
});

    }

    public void showDialog() {
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_layout);

         admin=dialog.findViewById(R.id.Administration_login);
         teacher=dialog.findViewById(R.id.Teacher_login);
         student=dialog.findViewById(R.id.Student_login);

         admin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Display=findViewById(R.id.selected_option);
                 Display.setText("Administrator");
                 dialog.dismiss();
             }
         });
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display=findViewById(R.id.selected_option);
                Display.setText("Teacher");
                dialog.dismiss();
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display=findViewById(R.id.selected_option);
                Display.setText("Student");
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);


    }
}

