package com.example.android.studentspaces;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Page1 extends Activity {

    Button btnSignIn1, btnSignUp1,newloginn;
    LoginDataBaseAdapter loginDataBaseAdapter;
    public static final String myPreference = "MyPreferences";
    public static final String username = "nameKey";
    public static final String password = "passwordKey";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sharedPreferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE);

        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);//Initialized the object
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        // Get The Reference Of Buttons
        btnSignIn1=(Button)findViewById(R.id.buttonSignIN);
        btnSignUp1=(Button)findViewById(R.id.buttonSignUP);
        newloginn=(Button)findViewById(R.id.newlogin);

        newloginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intentLogin);
            }
        });



        // Set OnClick Listener on SignUp button
        btnSignUp1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  and Start The Activity
                Intent intentSignUP=new Intent(getApplicationContext(),SignUp_Reg.class);
                startActivity(intentSignUP);
            }
        });
    }
    // Methods to handleClick Event of Sign In Button
    public void signIn(View V)
    {
        final Dialog dialog = new Dialog(Page1.this);
        dialog.setContentView(R.layout.activity_sign_in_login);
        dialog.setTitle("Login");

        // get the References of views
        final EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
        final  EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);

        Button btnSignIn1=(Button)dialog.findViewById(R.id.buttonSignIn);

        // Set On ClickListener
        btnSignIn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName=editTextUserName.getText().toString();
                String userPassword=editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);

                // check if the Stored password matches with  Password entered by user
                if(userPassword.equals(storedPassword))
                {
                    Toast.makeText(Page1.this, "Congrats: Login Successful", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(username, userName);
                    editor.putString(password, userPassword);
                    editor.apply();
                    Intent NewConfirm = new Intent(Page1.this,studymode.class);
                    NewConfirm.putExtra("userName",userName);
                    Page1.this.startActivity(NewConfirm);
                }
                else
                {
                    Toast.makeText(Page1.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
}

