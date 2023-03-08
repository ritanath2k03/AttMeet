package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Login extends AppCompatActivity {
    TextView Select,Display,admin,teacher,student;
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference reference;
    Authentication_adapter adapter;
    ArrayList<Authentication_model> arrayList;
 TextView Cancel,Submit;
TextInputEditText  Email=null,University_name=null,College_name=null,Id=null,Password=null;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Select=findViewById(R.id.Menuoption_textViewbtn);
        University_name=findViewById(R.id.University_login);
        Email=findViewById(R.id.Email_login);
        College_name=findViewById(R.id.College_login);
        Id=findViewById(R.id.Id_login);
        Cancel=findViewById(R.id.Cancel_button);
        Submit=findViewById(R.id.Submit_button);
        Password=findViewById(R.id.Password_login);
        arrayList=new ArrayList<>();
        adapter=new Authentication_adapter(arrayList,this);
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
                 Submit.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         String got_collegeName= College_name.getText().toString().toUpperCase();

                         String got_collegeId= Id.getText().toString().toUpperCase();
                         String got_university= University_name.getText().toString().toUpperCase();
                         String got_email= Email.getText().toString().toUpperCase();
                         String got_password=Password.getText().toString();
                         reference=db.getReference().child("Administration").child(got_university).child(got_collegeId);



                         if (TextUtils.isEmpty(got_collegeName)){
                             College_name.setError("Enter a Valid Name");
                             return;
                         }

                         else if(TextUtils.isEmpty(got_email)){
                             Email.setError("Enter a Valid Email");
                             return;
                         } else if (TextUtils.isEmpty(got_password)) {
                             Password.setError("This is mandatory");

                         }
                         else{

                             reference.addValueEventListener(new ValueEventListener() {
                                 @Override
                                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                                     arrayList.clear();
                                     for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                                         Authentication_model model=dataSnapshot.getValue((Authentication_model.class));
                                         if(model==null){
                                             arrayList.add(new Authentication_model("None","-1"));
                                         }else{
                                             
                                             arrayList.add(model);
                                         }
                                     }
                                     adapter.notifyDataSetChanged();
                                 }

                                 @Override
                                 public void onCancelled(@NonNull DatabaseError error) {

                                 }
                             });
                             Log.d("Names",arrayList.toString());

                             if(arrayList.contains(new Authentication_model(got_email,got_password))){
                                 Toast.makeText(Login.this, "Next Activity", Toast.LENGTH_SHORT).show();

                             }
                             else {
                                 Toast.makeText(Login.this, "Enter Correct Credentials", Toast.LENGTH_SHORT).show();
                             }

                         }




                     }
                 });
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

