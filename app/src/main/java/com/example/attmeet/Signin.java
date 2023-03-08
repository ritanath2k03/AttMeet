package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Signin extends AppCompatActivity   {
TextView cancel,submit;
ImageView Profile_pic;
TextInputEditText Email=null,University_name=null,College_name=null,Id=null,Password=null;
    String url="fdsfagasgdfbdagrehbfxb";
    String[] permission = {"android.permission.CAMERA"};
FirebaseAuth signinAuth=FirebaseAuth.getInstance();

FirebaseDatabase database=FirebaseDatabase.getInstance();
DatabaseReference reference=database.getReference();
ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Profile_pic=findViewById(R.id.Profile_pic);

        University_name=findViewById(R.id.University_signin);
        Email=findViewById(R.id.Email_signin);
        College_name=findViewById(R.id.College_signin);
        Id=findViewById(R.id.Id_signin);
        cancel=findViewById(R.id.Cancel_button);
        submit=findViewById(R.id.Submit_button);
        Password=findViewById(R.id.Password_signin);
        //For Sending Firebase
        Profile_pic=findViewById(R.id.Profile_pic);
//

        String Got_CollegeName= College_name.getText().toString();
        String Got_CollegeId= Id.getText().toString();
        String Got_University= University_name.getText().toString();
        String Got_Email= Email.getText().toString();
        String Got_Password=Password.getText().toString();

submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        //For Sending Firebase
        submit_data_in_Firebase();



    }
});

cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(Signin.this,MainActivity.class));
    }
});
    }

    private void submit_data_in_Firebase() {
        String got_collegeName= College_name.getText().toString().toUpperCase();
        String got_collegeId= Id.getText().toString().toUpperCase();
        String got_university= University_name.getText().toString().toUpperCase();
        String got_email= Email.getText().toString().toUpperCase();
        String got_password=Password.getText().toString();
        HashMap<String,Object> map=new HashMap<>();
        map.put("Email",got_email);
        map.put("Password",got_password);


    if (TextUtils.isEmpty(got_collegeName)){
        College_name.setError("Enter a Valid Name");
        return;
    }

        else if (TextUtils.isEmpty(got_collegeId)){
        Id.setError("Enter a Valid id");
        return;
    }
        else if(TextUtils.isEmpty(got_university)){
            University_name.setError("Enter a Valid University");
            return;
        }
      else if(TextUtils.isEmpty(got_email)){
            Email.setError("Enter a Valid Email");
            return;
        } else if (TextUtils.isEmpty(got_password)) {
          Password.setError("This is mandatory");

    } else{
            reference.child("Administration").child(got_university).child(got_collegeId).child(got_collegeName)
                    .setValue(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            createAccount();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Signin.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }

    }

    private void createAccount() {
        progressDialog=new ProgressDialog(Signin.this);
        String Got_Email= Email.getText().toString();
        String Got_Password=Password.getText().toString();
        progressDialog.setTitle("Just a moment....");
        progressDialog.setMessage("We are Creating Your Account");
        progressDialog.show();
         signinAuth.createUserWithEmailAndPassword(Got_Email,Got_Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 progressDialog.dismiss();
                 if(task.isSuccessful()){
                     Toast.makeText(Signin.this, "New Activity", Toast.LENGTH_SHORT).show();
                     Email.setText("");
                     University_name.setText("");
                     College_name.setText("");
                     Password.setText("");
                     Id.setText("");
                 }
                 else {
                     Toast.makeText(Signin.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                 }
             }
         });
     }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera Permission accepted", Toast.LENGTH_SHORT).show();
            }
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setRoom(url)
                    .build();
            JitsiMeetActivity.launch(this, options);

        }else {
            Toast.makeText(this, "Camera Permission Decline", Toast.LENGTH_SHORT).show();
        }

    }







    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Stop","on");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("OnResume","on");
    }

    public void ChooseProfilePic(View view) {


    }
}