package com.example.and;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

public class TenantHomeBage extends AppCompatActivity {
   SessionManager session ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_home_bage);
        session = new SessionManager(this);
        Toast.makeText(this, "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        session.checkLogin();
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        Log.d("", "onCreate: *****************************"+email);
    }


    public void search(View view) {
        Intent intent = new Intent(this,search.class) ;
        startActivity(intent);
    }

    public void home(View view) {

    }

    public void history(View view) {
    }

    public void logout(View view) {
        session.logoutUser();
        Intent intent = new Intent(this,Sign_in.class) ;
        startActivity(intent);

    }
}