package com.example.android.studentspaces;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FeedbackForm extends AppCompatActivity {

    private RadioGroup rateLecture, rateFaculty;
    private RadioButton rateLectureSelection, rateFacultySelection;
    private EditText comments;
    private Button submit;
    private int rateLecSelection, rateFacSelection;
    private String commentss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_form);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final int selection = getIntent().getExtras().getInt("selection");
        rateLecture = (RadioGroup) findViewById(R.id.radioGroupRateLecture);
        rateFaculty = (RadioGroup) findViewById(R.id.radioGroupRateFaculty);
        comments = (EditText) findViewById(R.id.comments);
        commentss = comments.getText().toString();

        submit = (Button) findViewById(R.id.submitbtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateLecSelection = rateLecture.getCheckedRadioButtonId();
                rateFacSelection = rateFaculty.getCheckedRadioButtonId();
                if(rateLecSelection == -1 || rateFacSelection == -1 ){
                    Toast.makeText(FeedbackForm.this, "Please give both ratings", Toast.LENGTH_SHORT).show();
                }
                else{
                    rateLectureSelection = (RadioButton) findViewById(rateLecSelection);
                    rateFacultySelection = (RadioButton) findViewById(rateFacSelection);
                    Toast.makeText(FeedbackForm.this, "All ratings saved successfully", Toast.LENGTH_SHORT).show();
                    //Database code for storing data into database
                    Intent FeedbackIntent = new Intent(FeedbackForm.this, Feedback.class);
                    FeedbackIntent.putExtra("btnSwitch", selection);
                    FeedbackForm.this.startActivityForResult(FeedbackIntent, 1);
                }
            }
        });
    }
}