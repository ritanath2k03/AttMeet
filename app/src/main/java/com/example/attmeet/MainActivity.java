package com.example.attmeet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private TextView textView,textView1;
    private Handler handler,handler1;

    private long startTime, currentTime, finishedTime = 0;
    private int duration = 10000 / 4,duration1=8000/4;// 1 character is equal to 1 second. if want to
    // reduce. can use as divide
    // by 2,4,8

    private int endTime = 0;
    private Button login_btn,signin_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.Welcome_word);
        textView.setText("WELCOME TO ");// length of string is 22
        handler = new Handler();
        startTime = Long.valueOf(System.currentTimeMillis());
        currentTime = startTime;
//Button declaration
        login_btn=findViewById(R.id.login);
        signin_btn=findViewById(R.id.signin);

        textView1 = (TextView) findViewById(R.id.Welcome_word2);
        textView1.setText("ATT MEET ");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                currentTime = Long.valueOf(System.currentTimeMillis());
                finishedTime = Long.valueOf(currentTime)
                        - Long.valueOf(startTime);

                if (finishedTime >= duration+30) {
                    show();
                } else {
                    endTime = (int) (finishedTime / 250);// divide this by
                    // 1000,500,250,125
                    Spannable spannableString = new SpannableString(textView
                            .getText());
                    spannableString.setSpan(new ForegroundColorSpan(
                                    Color.CYAN), 0, endTime,
                            Spannable.SPAN_COMPOSING);

                    textView.setText(spannableString);
                    handler.postDelayed(this, 2);


                }
            }
        }, 2);


        handler1=new Handler();
        startTime = Long.valueOf(System.currentTimeMillis());
        currentTime = startTime;

        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {

                currentTime = Long.valueOf(System.currentTimeMillis());
                finishedTime = Long.valueOf(currentTime)
                        - Long.valueOf(startTime);

                if (finishedTime >= duration1+30) {

                }
                else {
                    endTime = (int) (finishedTime / 250);// divide this by
                    // 1000,500,250,125
                    Spannable spannableString = new SpannableString(textView1
                            .getText());
                    spannableString.setSpan(new ForegroundColorSpan(
                                    Color.GREEN), 0, endTime,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    textView1.setText(spannableString);
                    handler1.postDelayed(this, 2);


                }
            }
        }, 2);


        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_200)));
    }

    private void show() {
FirebaseAuth auth=FirebaseAuth.getInstance();
if(auth.getUid()!=null){
    Intent intent=new Intent(MainActivity.this,Administration.class);
//    intent.putExtra("College_name",getIntent().getStringExtra("College_name"));
    startActivity(intent);

}

signin_btn.setVisibility(View.VISIBLE);
login_btn.setVisibility(View.VISIBLE);
        Dialog dialog=new Dialog(this);
signin_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this,Signin.class));

    }
});
login_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this,Login.class));
    }
});
    }


}