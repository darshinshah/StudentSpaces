package com.example.android.studentspaces;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class syllabus extends AppCompatActivity {

    Button spbtn, mathsbtn, coabtn, mpbtn, iotbtn, cnbtn;
    int selection;
    FloatingActionButton UserSetting,UserSettingNotesReminders,UserSettingFeedBack,StudyMode;
    Animation FabOpen,FabClose,FabRClockwise,FabRAClockwise;
    boolean isOpen= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mathsbtn = (Button)findViewById(R.id.mathsbtn);
        mpbtn = (Button)findViewById(R.id.mpbtn);
        cnbtn = (Button)findViewById(R.id.cnbtn);
        spbtn = (Button)findViewById(R.id.spbtn);
        coabtn = (Button)findViewById(R.id.coabtn);
        iotbtn = (Button)findViewById(R.id.iotbtn);

        mathsbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                selection = 1;
                Intent i = new Intent(syllabus.this, viewsyllabus.class);
                i.putExtra("selection", selection);
                syllabus.this.startActivity(i);// "startActivity(i)" will also work
            }
        });
        mpbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                selection = 2;
                Intent i = new Intent(syllabus.this, viewsyllabus.class);
                i.putExtra("selection", selection);
                syllabus.this.startActivity(i);

            }
        });
        cnbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                selection = 3;
                Intent i = new Intent(syllabus.this, viewsyllabus.class);
                i.putExtra("selection", selection);
                syllabus.this.startActivity(i);
            }
        });
        spbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                selection = 4;
                Intent i = new Intent(syllabus.this, viewsyllabus.class);
                i.putExtra("selection", selection);
                syllabus.this.startActivity(i);

            }
        });
        coabtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                selection = 5;
                Intent i = new Intent(syllabus.this, viewsyllabus.class);
                i.putExtra("selection", selection);
                syllabus.this.startActivity(i);

            }
        });

        iotbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                selection = 6;
                Intent i = new Intent(syllabus.this, viewsyllabus.class);
                i.putExtra("selection", selection);
                syllabus.this.startActivity(i);

            }
        });

        UserSetting = (FloatingActionButton)findViewById(R.id.UserSettings);
        UserSettingFeedBack = (FloatingActionButton)findViewById(R.id.UserSettingsFeedback);
        UserSettingNotesReminders = (FloatingActionButton)findViewById(R.id.UserSettingsNotesReminders);
        StudyMode=(FloatingActionButton)findViewById(R.id.StudyMode);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        FabRAClockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);
        final Animation FadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        final Animation FadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);


        UserSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isOpen)
                {
                    UserSettingNotesReminders.startAnimation(FabClose);
                    UserSettingFeedBack.startAnimation(FabClose);
                    StudyMode.startAnimation(FabClose);
                    UserSetting.startAnimation(FabRAClockwise);
                    UserSettingNotesReminders.setClickable(false);
                    UserSettingFeedBack.setClickable(false);
                    StudyMode.setClickable(false);
                    isOpen = false;
                    spbtn.startAnimation(FadeIn);
                    coabtn.startAnimation(FadeIn);
                    cnbtn.startAnimation(FadeIn);
                    mathsbtn.startAnimation(FadeIn);
                    iotbtn.startAnimation(FadeIn);
                    mpbtn.startAnimation(FadeIn);

                }
                else
                {
                    UserSettingNotesReminders.startAnimation(FabOpen);
                    UserSettingFeedBack.startAnimation(FabOpen);
                    StudyMode.startAnimation(FabOpen);
                    UserSetting.startAnimation(FabRClockwise);
                    UserSettingNotesReminders.setClickable(true);
                    UserSettingFeedBack.setClickable(true);
                    StudyMode.setClickable(true);
                    isOpen = true;
                    spbtn.startAnimation(FadeOut);
                    coabtn.startAnimation(FadeOut);
                    cnbtn.startAnimation(FadeOut);
                    mathsbtn.startAnimation(FadeOut);
                    iotbtn.startAnimation(FadeOut);
                    mpbtn.startAnimation(FadeOut);


                }

            }
        });

        UserSettingNotesReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NotesIntent = new Intent(syllabus.this, Notes_Reminders.class);
                syllabus.this.startActivity(NotesIntent);
            }
        });

        UserSettingFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent FeedbackIntent = new Intent(syllabus.this, Feedback.class);
                int btnSwitch = 0;
                FeedbackIntent.putExtra("btnSwitch", btnSwitch);
                syllabus.this.startActivity(FeedbackIntent);
            }
        });

        StudyMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StudyModeIntent = new Intent(syllabus.this, studymode.class);
                syllabus.this.startActivity(StudyModeIntent);
            }
        });
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(syllabus.this, studymode.class));
        finish();
    }
}