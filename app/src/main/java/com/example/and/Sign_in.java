package com.example.and;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Sign_in extends AppCompatActivity {
    CheckBox checkBox ;
    EditText txtEmail, txtPassword;
    SessionManager sessionManager;
    DatabaseHelper databaseHelper;
    ArrayList<String> emails, passwords, emails1, passwords1;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(this);
        setContentView(R.layout.activity_sign_in);
        txtEmail = findViewById(R.id.txtemail);
        txtPassword = findViewById(R.id.password);
        checkBox = findViewById(R.id.checkBox) ;
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            txtEmail.setText(loginPreferences.getString("username", ""));
            txtPassword.setText(loginPreferences.getString("password", ""));
            checkBox.setChecked(true);
        }
        Toast.makeText(this, "User Login Status: " + sessionManager.isLoggedIn(), Toast.LENGTH_LONG).show();
    }

    public void login(View view) {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if (email.trim().length() > 0 && password.trim().length() > 0) {
            databaseHelper = new DatabaseHelper(this);

            if (email.equals("") || password.equals(""))
                Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkuserpass = databaseHelper.checkusernamepassword(email, password);
                Boolean checkuserpass1 = databaseHelper.checkusernamepasswordAgency(email, password);
                if (checkuserpass) {
                    Toast.makeText(Sign_in.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                    if (checkBox.isChecked()) {
                        loginPrefsEditor.putBoolean("saveLogin", true);
                        loginPrefsEditor.putString("username", email);
                        loginPrefsEditor.putString("password", password);
                        loginPrefsEditor.commit();
                    } else {
                        loginPrefsEditor.clear();
                        loginPrefsEditor.commit();
                    }
                    Intent intent = new Intent(getApplicationContext(), TenantHomeBage.class);
                    startActivity(intent);
                    if (checkBox.isChecked()) {
                        loginPrefsEditor.putBoolean("saveLogin", true);
                        loginPrefsEditor.putString("username", email);
                        loginPrefsEditor.putString("password", password);
                        loginPrefsEditor.commit();
                    } else {
                        loginPrefsEditor.clear();
                        loginPrefsEditor.commit();
                    }
                    sessionManager.createLoginSession("user", email);
                }else if(checkuserpass1){
                    sessionManager.createLoginSession("user", email);
                    Intent intent = new Intent(getApplicationContext(), AgencyHomePage.class);
                    startActivity(intent);


                } else {
                    Toast.makeText(Sign_in.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}







