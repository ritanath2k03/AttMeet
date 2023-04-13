package com.example.attmeet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.HashMap;

public class Schedule extends AppCompatActivity {
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    FirebaseAuth auth=FirebaseAuth.getInstance();
    DatabaseReference reference=db.getReference("Users");
    TextView headername;
Authentication_adapter adapter_mt,adapter_tt,adapter_wt,adapter_tht,adapter_ft;
    RecyclerView recyclerView_mt,recyclerView_tt,recyclerView_wt,recyclerView_tht,recyclerView_ft;

    ArrayList<Authentication_model> arrayList_mt,arrayList_tt,arrayList_wt,arrayList_tht,arrayList_ft;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_schedule);
        recyclerView_mt = findViewById(R.id.Monday_t);
        recyclerView_tt = findViewById(R.id.Tuesday_t);
        recyclerView_wt = findViewById(R.id.Wednesday_t);
        recyclerView_tht = findViewById(R.id.Thursday_t);
        recyclerView_ft = findViewById(R.id.Friday_t);
        headername = findViewById(R.id.header_name_t);

        reference.child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Authentication_model model = snapshot.getValue(Authentication_model.class);
                headername.setText(model.getTeacherName() + "  " + model.getTeacherSubject());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //for monday
        arrayList_mt = new ArrayList<>();
        adapter_mt = new Authentication_adapter(arrayList_mt, this);
        recyclerView_mt.setHasFixedSize(true);
        recyclerView_mt.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView_mt.setAdapter(adapter_mt);

        reference.child(auth.getUid()).child("Schedule").child("Monday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList_mt.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Authentication_model model = dataSnapshot.getValue(Authentication_model.class);
                    arrayList_mt.add(model);
                }
                adapter_mt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //for Tuesday

        arrayList_tt = new ArrayList<>();
        adapter_tt = new Authentication_adapter(arrayList_tt, this);
        recyclerView_tt.setHasFixedSize(true);
        recyclerView_tt.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView_tt.setAdapter(adapter_tt);

        reference.child(auth.getUid()).child("Schedule").child("Tuesday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList_tt.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Authentication_model model = dataSnapshot.getValue(Authentication_model.class);
                    arrayList_tt.add(model);
                }
                adapter_tt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //for Wednesday

        arrayList_wt = new ArrayList<>();
        adapter_wt = new Authentication_adapter(arrayList_wt, this);
        recyclerView_wt.setHasFixedSize(true);
        recyclerView_wt.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView_wt.setAdapter(adapter_wt);

        reference.child(auth.getUid()).child("Schedule").child("Wednesday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList_wt.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Authentication_model model = dataSnapshot.getValue(Authentication_model.class);
                    arrayList_wt.add(model);
                }
                adapter_wt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//For Thursday
        arrayList_tht = new ArrayList<>();
        adapter_tht = new Authentication_adapter(arrayList_tht, this);
        recyclerView_tht.setHasFixedSize(true);
        recyclerView_tht.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView_tht.setAdapter(adapter_tht);

        reference.child(auth.getUid()).child("Schedule").child("Thursday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList_tht.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Authentication_model model = dataSnapshot.getValue(Authentication_model.class);
                    arrayList_tht.add(model);
                }
                adapter_tht.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //For Friday


        arrayList_ft = new ArrayList<>();
        adapter_ft = new Authentication_adapter(arrayList_ft, this);
        recyclerView_ft.setHasFixedSize(true);
        recyclerView_ft.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView_ft.setAdapter(adapter_ft);

        reference.child(auth.getUid()).child("Schedule").child("Friday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList_ft.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Authentication_model model = dataSnapshot.getValue(Authentication_model.class);
                    arrayList_ft.add(model);
                }
                adapter_ft.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

}
