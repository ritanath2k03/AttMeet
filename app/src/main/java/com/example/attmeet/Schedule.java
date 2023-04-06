package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetActivityDelegate;
import org.jitsi.meet.sdk.JitsiMeetActivityInterface;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.JitsiMeetView;

import java.net.MalformedURLException;
import java.net.URL;

public class Schedule extends AppCompatActivity {
Toolbar toolbar;
ImageView Subject;
FirebaseAuth auth =FirebaseAuth.getInstance();
FabricViewStateManager fabricViewStateManager;

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

            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL(""))

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

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera Permission accepted", Toast.LENGTH_SHORT).show();
            }
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()

                    .setRoom(auth.getUid())

                    .build();
       JitsiMeetActivity.launch(this, options);

        } else {
            Toast.makeText(this, "Camera Permission Decline", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "On destroy", Toast.LENGTH_SHORT).show();
    }
int i=0;
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "On stop", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "On Restart", Toast.LENGTH_SHORT).show();

        
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "on Start", Toast.LENGTH_SHORT).show();
    }
}
