package com.example.android.studentspaces;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class Notes_Reminders extends AppCompatActivity {

    FloatingActionButton UserSetting, UserSettingNotesReminders, UserSettingFeedBack, StudyMode; 
    Animation FabClose, FabOpen, FabRClockwise, FabRAClockwise;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes__reminders);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        UserSetting = (FloatingActionButton)findViewById(R.id.UserSettings);
        StudyMode = (FloatingActionButton)findViewById(R.id.StudyMode);
        UserSettingNotesReminders = (FloatingActionButton)findViewById(R.id.UserSettingsNotesReminders);
        UserSettingFeedBack = (FloatingActionButton)findViewById(R.id.UserSettingsFeedback);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        FabRAClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);

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
                    LinearLayout activitynotesandreminders = (LinearLayout) findViewById(R.id.activity_notes_and_reminders);
                    for(int i = 0; i < 19000000;i++){}
                    activitynotesandreminders.setAlpha(1.0f);
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
                    LinearLayout activitynotesandreminders = (LinearLayout) findViewById(R.id.activity_notes_and_reminders);
                    for(int i = 0; i < 19000000;i++){}
                    activitynotesandreminders.setAlpha(0.0f);
                }

            }
        });

        UserSettingNotesReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NotesIntent = new Intent(Notes_Reminders.this,Notes_Reminders.class);
                Notes_Reminders.this.startActivity(NotesIntent);
            }
        });

        UserSettingFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent FeedbackIntent = new Intent(Notes_Reminders.this,Feedback.class);
                int btnSwitch = 0;
                FeedbackIntent.putExtra("btnSwitch", btnSwitch);
                Notes_Reminders.this.startActivity(FeedbackIntent);
            }
        });

        StudyMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StudyModeIntent = new Intent(Notes_Reminders.this,studymode.class);
                Notes_Reminders.this.startActivity(StudyModeIntent);
            }
        });
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(Notes_Reminders.this, studymode.class));
        finish();
    }
}