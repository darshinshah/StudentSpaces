package com.example.android.studentspaces;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.view.View;

public class viewsyllabus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewsyllabus);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        int selection = getIntent().getExtras().getInt("selection");

        switch(selection) {
            case 1: {
                ImageView maths1, maths2;
                maths1 = (ImageView) findViewById(R.id.maths1);
                maths1.setImageResource(R.drawable.maths1);
                maths2 = (ImageView) findViewById(R.id.maths2);
                maths2.setImageResource(R.drawable.maths2);
                maths1.setVisibility(View.VISIBLE);
                maths2.setVisibility(View.VISIBLE);
                break;
            }
            case 2: {
                ImageView mp1, mp2;
                mp1 = (ImageView) findViewById(R.id.mp1);
                mp1.setImageResource(R.drawable.mp1);
                mp2 = (ImageView) findViewById(R.id.mp2);
                mp2.setImageResource(R.drawable.mp2);
                mp1.setVisibility(View.VISIBLE);
                mp2.setVisibility(View.VISIBLE);
                break;
            }
            case 3: {
                ImageView cn1, cn2;
                cn1 = (ImageView) findViewById(R.id.cn1);
                cn1.setImageResource(R.drawable.cn1);
                cn2 = (ImageView) findViewById(R.id.cn2);
                cn2.setImageResource(R.drawable.cn2);
                cn1.setVisibility(View.VISIBLE);
                cn2.setVisibility(View.VISIBLE);

                break;
            }
            case 4: {
                ImageView sp1, sp2;
                sp1 = (ImageView) findViewById(R.id.sp1);
                sp1.setImageResource(R.drawable.sp1);
                sp2 = (ImageView) findViewById(R.id.sp2);
                sp2.setImageResource(R.drawable.sp2);
                sp1.setVisibility(View.VISIBLE);
                sp2.setVisibility(View.VISIBLE);
                break;
            }
            case 5: {
                ImageView coa1, coa2;
                coa1 = (ImageView) findViewById(R.id.coa1);
                coa1.setImageResource(R.drawable.coa1);
                coa2 = (ImageView) findViewById(R.id.coa2);
                coa2.setImageResource(R.drawable.coa2);
                coa1.setVisibility(View.VISIBLE);
                coa2.setVisibility(View.VISIBLE);
                break;
            }
            case 6: {
                ImageView iot;
                iot = (ImageView) findViewById(R.id.iot);
                iot.setImageResource(R.drawable.iot);
                iot.setVisibility(View.VISIBLE);
                break;
            }
        }

    }
}
