package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Enrollment_Teacher_Student extends AppCompatActivity {
TextInputEditText Name,Id,Subject,Email,Password;
TextView submit,Cancel;
FirebaseDatabase db=FirebaseDatabase.getInstance();
DatabaseReference reference;
DatabaseReference reference1=db.getReference("Users");
FirebaseAuth auth=FirebaseAuth.getInstance();
String CollegeId,Clg_Email,Clg_Name,Clg_Password,Clg_Uid,University;
ProgressDialog progressDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_teacher_student);
        Name=findViewById(R.id.Teacher_Name_signup);
        Id=findViewById(R.id.Teacher_Id_signup);
        Subject=findViewById(R.id.Teacher_Subject);

        Cancel=findViewById(R.id.Cancel_button);
        submit=findViewById(R.id.SubmitTextView);


        reference=db.getReference("Users").child(auth.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                list.clear();

                if(FirebaseAuth.getInstance().getUid()!=null){
                    Authentication_model model=snapshot.getValue(Authentication_model.class);
//Log.d("Id",model1.getCollegeId());
University=model.getUniversity();
Clg_Name=model.getName();
Clg_Password=model.getPassword();
Clg_Uid=model.getUid();
CollegeId=model.getCollegeId();
Clg_Email=model.getEmail();
                            submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }

            private void CreateAccount() {
                Email=findViewById(R.id.Email_Teacher_signin);
                Password=findViewById(R.id.Password_Teacher_signin);
                String Got_Email=Email.getText().toString();
                String Got_Password=Password.getText().toString();
               progressDialog=new ProgressDialog(Enrollment_Teacher_Student.this);
                progressDialog.setTitle("Just a moment....");
                progressDialog.setMessage("We are Creating Your Account");
                progressDialog.show();
                auth.createUserWithEmailAndPassword(Got_Email,Got_Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                StoreData();
                            }


                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Enrollment_Teacher_Student.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            } });
                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void StoreData() {
        HashMap<String,Object> map=new HashMap<>();

        map.put("TeacherEmail",Email.getText().toString().toUpperCase());
        map.put("TeacherPassword",Password.getText().toString());
        reference1.child(University).child(CollegeId).child(Clg_Name).child("Teacher").child(FirebaseAuth.getInstance().getUid()).setValue(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            AddData();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    private void AddData() {
        HashMap<String,Object> map1=new HashMap<>();
        map1.put("TeacherId",Id.getText().toString().toUpperCase());
        map1.put("TeacherName",Name.getText().toString().toUpperCase());
        map1.put("TeacherSubject",Subject.getText().toString().toUpperCase());
        map1.put("TeacherEmail",Email.getText().toString().toUpperCase());
        map1.put("TeacherPassword",Password.getText().toString());
        reference.child("Teachers").child(FirebaseAuth.getInstance().getUid()).setValue(map1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
progressDialog.dismiss();
                Email.setText("");
                Name.setText("");
                Subject.setText("");
                Password.setText("");
                Id.setText("");
                auth.signInWithEmailAndPassword(Clg_Email,Clg_Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
if (task.isSuccessful()){
    Toast.makeText(Enrollment_Teacher_Student.this, "Teacher Enrolled", Toast.LENGTH_SHORT).show();

}
                    }
                });
            }
        });
    }
}