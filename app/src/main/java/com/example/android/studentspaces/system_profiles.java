package com.example.android.studentspaces;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;


public class system_profiles extends AppCompatActivity{

    FloatingActionButton UserSetting,UserSettingNotesReminders,UserSettingFeedBack,StudyMode;
    Animation FabOpen,FabClose,FabRClockwise,FabRAClockwise,FadeIn,FadeOut;
    boolean isOpen= false;
    Button mode,ring,vibrate,silent,socialBlock;
    private AudioManager myAudioManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_profiles);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        UserSetting = (FloatingActionButton)findViewById(R.id.UserSettings);
        UserSettingFeedBack = (FloatingActionButton)findViewById(R.id.UserSettingsFeedback);
        UserSettingNotesReminders = (FloatingActionButton)findViewById(R.id.UserSettingsNotesReminders);
        StudyMode=(FloatingActionButton)findViewById(R.id.StudyMode);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        FabRAClockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);



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
                    RelativeLayout activitysystemprofiles = (RelativeLayout) findViewById(R.id.activity_system_profiles);
                    for(int i = 0; i < 19000000;i++){}
                    activitysystemprofiles.setAlpha(1.0f);
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
                    RelativeLayout activitysystemprofiles = (RelativeLayout) findViewById(R.id.activity_system_profiles);
                    for(int i = 0; i < 19000000;i++){}
                    activitysystemprofiles.setAlpha(0.0f);
                }

            }
        });

        UserSettingNotesReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NotesIntent = new Intent(system_profiles.this,Notes_Reminders.class);
                system_profiles.this.startActivity(NotesIntent);
            }
        });

        UserSettingFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent FeedbackIntent = new Intent(system_profiles.this,Feedback.class);
                int btnSwitch = 0;
                FeedbackIntent.putExtra("btnSwitch", btnSwitch);
                system_profiles.this.startActivity(FeedbackIntent);
            }
        });

        StudyMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StudyModeIntent = new Intent(system_profiles.this,studymode.class);
                system_profiles.this.startActivity(StudyModeIntent);
            }
        });

        socialBlock = (Button) findViewById(R.id.socialBlock);
        socialBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*class NotificationService extends NotificationListenerService{
                    Context context;

                    @Override
                    public void onCreate(){
                        super.onCreate();
                        context = getApplicationContext();
                    }
                    public void onNotificationPosted(StatusBarNotification sbn){
                        cancelAllNotifications();
                    }

                }*/
                Toast.makeText(getApplicationContext(),"opening settings : choose apps to block notifications",Toast.LENGTH_LONG).show();
                Intent SystemSettingsBlock = new Intent(android.provider.Settings.ACTION_APPLICATION_SETTINGS);
                startActivity(SystemSettingsBlock);
            }
        });


        //*****************************************ringer vibrate silent modes
        vibrate=(Button)findViewById(R.id.vibrate);
        ring=(Button)findViewById(R.id.Ringer);
        silent=(Button)findViewById(R.id.silent);
        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(getApplicationContext(),"Now in Vibrate Mode",
                        Toast.LENGTH_SHORT).show();
            }
        });

        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(getApplicationContext(),"Now in Ringing Mode",
                        Toast.LENGTH_SHORT).show();
            }
        });

        silent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(getApplicationContext(),"Now in silent Mode",
                        Toast.LENGTH_SHORT).show();
            }
        });


        //**************** wifi on off code.
        Switch toggle = (Switch) findViewById(R.id.wifi_switch);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleWiFi(true);
                    Toast.makeText(getApplicationContext(), "Wi-Fi Enabled!", Toast.LENGTH_LONG).show();
                } else {
                    toggleWiFi(false);
                    Toast.makeText(getApplicationContext(), "Wi-Fi Disabled!", Toast.LENGTH_LONG).show();
                }
            }});}

    public void toggleWiFi(boolean status) {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (status == true && !wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        } else if (status == false && wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }
}