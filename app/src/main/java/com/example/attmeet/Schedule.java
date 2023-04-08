package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.mapbuffer.ReadableMapBuffer;
import com.facebook.react.modules.core.PermissionListener;
import com.facebook.react.uimanager.FabricViewStateManager;
import com.facebook.react.uimanager.StateWrapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jitsi.meet.sdk.BroadcastEvent;
import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetActivityDelegate;
import org.jitsi.meet.sdk.JitsiMeetActivityInterface;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.JitsiMeetOngoingConferenceService;
import org.jitsi.meet.sdk.JitsiMeetUserInfo;
import org.jitsi.meet.sdk.JitsiMeetView;
import org.jitsi.meet.sdk.log.JitsiMeetLogger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Schedule extends AppCompatActivity {
Toolbar toolbar;
ImageView Subject;
FirebaseAuth auth =FirebaseAuth.getInstance();
FirebaseDatabase db=FirebaseDatabase.getInstance();
DatabaseReference reference=db.getReference("Users");

String name;
    String[] permission = {"android.permission.CAMERA"};
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        toolbar=findViewById(R.id.Toolbar);
//setSupportActionBar(toolbar);
        toolbar.setTitle("Schedule");


        Toast.makeText(this, "on Create", Toast.LENGTH_SHORT).show();
        try {
            JitsiMeetUserInfo info=new JitsiMeetUserInfo();
            info.setDisplayName(auth.getUid());
            info.setEmail("abcd@test.com");
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL(""))
                    .setUserInfo(info)
                    .setAudioMuted(false)
                    .setConfigOverride("RequiredDisplayName",auth.getUid())
                    .build();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Subject=findViewById(R.id.subject);
        Subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions(permission, 2);
            }
        });
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(BroadcastEvent.Type.CONFERENCE_TERMINATED.getAction());
        BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(Schedule.this, "Terminated", Toast.LENGTH_SHORT).show();
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,intentFilter);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera Permission accepted", Toast.LENGTH_SHORT).show();
            }
            reference.child(auth.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Authentication_model model=snapshot.getValue(Authentication_model.class);
                    name=model.getTeacherName();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()

                    .setRoom(auth.getUid())

                    .build();

            Log.d("time","le time");
       JitsiMeetActivity.launch(this, options);


        } else {
            Toast.makeText(this, "Camera Permission Decline", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "On destroy", Toast.LENGTH_SHORT).show();
        Log.d("time","Time");

            JitsiMeetActivity jitsiMeetActivity=new JitsiMeetActivity();
            jitsiMeetActivity.leave();



    }
int i=0;
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "On stop", Toast.LENGTH_SHORT).show();
        JitsiMeetActivity jitsiMeetActivity=new JitsiMeetActivity();
        jitsiMeetActivity.leave();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "On Restart", Toast.LENGTH_SHORT).show();
        JitsiMeetActivity jitsiMeetActivity=new JitsiMeetActivity();

jitsiMeetActivity.leave();


        
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "on Start", Toast.LENGTH_SHORT).show();
    }
    protected void onConferenceJoined(String st) {
        JitsiMeetLogger.i("Conference joined: " + st);

        // Launch the service for the ongoing notification.



    }
}
