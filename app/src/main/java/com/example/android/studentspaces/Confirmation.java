package com.example.android.studentspaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);


        Window w = getWindow();
        w.setTitle("My title");
        final EditText etAge=(EditText) findViewById(R.id.etdAge);
        final EditText etName=(EditText) findViewById(R.id.etdName);
        final EditText etUsername=(EditText) findViewById(R.id.etdUSername);
        final EditText etEmail=(EditText) findViewById(R.id.etdEmail);
        final Button bContinue=(Button)findViewById(R.id.bContinue);


        bContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NotesIntent = new Intent(Confirmation.this,studymode.class);
                Confirmation.this.startActivity(NotesIntent);
            }



        });

    }
}
