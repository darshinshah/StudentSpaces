package com.example.android.studentspaces;

import android.app.Dialog;
import android.app.TaskStackBuilder;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class studymode extends AppCompatActivity {

    FloatingActionButton UserSetting, UserSettingNotesReminders, UserSettingFeedBack, StudyMode,UserAcc;
    Animation FabOpen, FabClose, FabRClockwise, FabRAClockwise, FadeIn, FadeOut;
    boolean isOpen = false;
    private long onRecentBackPressedTime;
    /*private static final long delay = 2000L;
    private boolean mRecentlyBackPressed = false;
    private Handler mExitHandler = new Handler();
    private Runnable mExitRunnable = new Runnable() {
        @Override
        public void run() {
            mRecentlyBackPressed=false;
        }
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studymode);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Initialize MediaPlayer object and set the music file that has to be played
        final MediaPlayer mp = MediaPlayer.create(studymode.this, R.raw.music);

        final Button playsound = (Button) findViewById(R.id.Sound);
        playsound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();//Start playing the music
            }
        });

        final Button SoundOff = (Button) findViewById(R.id.soundoff);
        SoundOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(mp.isPlaying())//If the music is playing...
                    {
                        mp.stop();//...stop playing
                    }
                    mp.prepare();//Sets the MediaPlayer object in prepared mode, so that when user presses play button after stopping, the song can be played again
                } catch (IOException e) {//Exception thrown by prepare(), which is why it is in a ty...catch statement
                    e.printStackTrace();
                }
            }
        });

        final Button timeLock = (Button) findViewById(R.id.timelock);

        final Button settings = (Button) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settings = new Intent(studymode.this, system_profiles.class);
                studymode.this.startActivity(settings);
            }

        });

        final Button browse = (Button) findViewById(R.id.browse);
        browse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent syllabus = new Intent(studymode.this, syllabus.class);
                studymode.this.startActivity(syllabus);
            }
        });

        UserSetting = (FloatingActionButton) findViewById(R.id.UserSettings);
        UserSettingFeedBack = (FloatingActionButton) findViewById(R.id.UserSettingsFeedback);
        UserSettingNotesReminders = (FloatingActionButton) findViewById(R.id.UserSettingsNotesReminders);
        UserAcc = (FloatingActionButton) findViewById(R.id.UserAccount);
        StudyMode = (FloatingActionButton) findViewById(R.id.StudyMode);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        FabRAClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);
        FadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        FadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        UserSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    UserSettingNotesReminders.startAnimation(FabClose);
                    UserAcc.startAnimation(FabClose);
                    UserSettingFeedBack.startAnimation(FabClose);
                    StudyMode.startAnimation(FabClose);
                    UserSetting.startAnimation(FabRAClockwise);
                    UserSettingNotesReminders.setClickable(false);
                    UserAcc.setClickable(false);
                    UserSettingFeedBack.setClickable(false);
                    StudyMode.setClickable(false);
                    isOpen = false;
                    browse.startAnimation(FadeIn);
                    playsound.startAnimation(FadeIn);
                    timeLock.startAnimation(FadeIn);
                    SoundOff.startAnimation(FadeIn);
                    settings.startAnimation(FadeIn);
                }
                else {
                    UserSettingNotesReminders.startAnimation(FabOpen);
                    UserSettingFeedBack.startAnimation(FabOpen);
                    UserAcc.startAnimation(FabOpen);
                    StudyMode.startAnimation(FabOpen);
                    UserSetting.startAnimation(FabRClockwise);
                    UserSettingNotesReminders.setClickable(true);
                    UserAcc.setClickable(true);
                    UserSettingFeedBack.setClickable(true);
                    StudyMode.setClickable(true);
                    isOpen = true;
                    browse.startAnimation(FadeOut);
                    playsound.startAnimation(FadeOut);
                    timeLock.startAnimation(FadeOut);
                    SoundOff.startAnimation(FadeOut);
                    settings.startAnimation(FadeOut);

                }
            }
        });

        UserSettingNotesReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NotesIntent = new Intent(studymode.this, Notes_Reminders.class);
                studymode.this.startActivity(NotesIntent);
            }
        });

        UserAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AccountIntent = new Intent(studymode.this, MainActivity.class);
                studymode.this.startActivity(AccountIntent);
            }
        });



        UserSettingFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent FeedbackIntent = new Intent(studymode.this, Feedback.class);
                int btnSwitch = 0;
                FeedbackIntent.putExtra("btnSwitch", btnSwitch);
                studymode.this.startActivity(FeedbackIntent);
            }
        });
        StudyMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StudyModeIntent = new Intent(studymode.this, studymode.class);
                studymode.this.startActivity(StudyModeIntent);
            }
        });
    }

    public void timelockfunction(View V) {
        final Dialog dialog = new Dialog(studymode.this);
        dialog.setContentView(R.layout.activity_timelockfunction);
        dialog.setTitle("Time Lock Your Device");
        Button timelocck = (Button) dialog.findViewById(R.id.buttonLock);
        final EditText mins = (EditText) dialog.findViewById(R.id.time);
        timelocck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String min1 = mins.getText().toString();
                Long mmm = Long.parseLong(min1);
                turnScreenOff(getApplicationContext(), mmm);
            }
        });
        dialog.show();
    }

    void turnScreenOff(final Context context, long time) {
        final DevicePolicyManager policyManager = (DevicePolicyManager) context
                .getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName adminReceiver = new ComponentName(context,
                ScreenOffAdminReceiver.class);
        boolean admin = policyManager.isAdminActive(adminReceiver);
        if (admin){
 /*           long startTime = System.currentTimeMillis();
            while((System.currentTimeMillis() - startTime) < (time * 1000)){
                policyManager.lockNow();
    This method is not efficient. Using threads is more efficient            }*/

            // Start thread
            final Thread t = new Thread(new Runnable(){
                @Override
                public void run(){
                    while(!Thread.currentThread().isInterrupted()){
                        try{
                            policyManager.lockNow();
                        }
                        catch(Exception e){
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            });
            t.start();

            // Schedule task to terminate thread in specified number of minutes
            ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
            exec.schedule(new Runnable(){
                @Override
                public void run(){
                    t.interrupt();
                }
            }, time, TimeUnit.MINUTES);
        }
        else {
            Toast.makeText(context, R.string.device_admin_not_enabled,
                    Toast.LENGTH_LONG).show();
        }
    }
    /*
    @Override
    public void onBackPressed() {
        if (mRecentlyBackPressed) {
            mExitHandler.removeCallbacks(mExitRunnable);
            mExitHandler = null;
            Intent exitIntent = new Intent(Intent.ACTION_MAIN);
            exitIntent.addCategory(Intent.CATEGORY_HOME);
            exitIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(exitIntent);
        }
        else
        {
            mRecentlyBackPressed = true;
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            mExitHandler.postDelayed(mExitRunnable, delay);
        }
    }*/
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - onRecentBackPressedTime > 2000) {
            onRecentBackPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "Please press BACK again to exit", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent exitIntent = new Intent(Intent.ACTION_MAIN);
        exitIntent.addCategory(Intent.CATEGORY_HOME);
        exitIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(exitIntent);
        finish();
    }
}