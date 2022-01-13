package com.example.and;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class AgencyHomePage extends AppCompatActivity {
    Button manage;
SessionManager sessionManager  ;
DatabaseHelper databaseHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_home_page);
        databaseHelper=new DatabaseHelper(this) ;
        manage = findViewById(R.id.managepropirty);
        sessionManager = new SessionManager(this) ;
        sessionManager.checkLogin();
        // get user data from session
        HashMap<String, String> user = sessionManager.getUserDetails();
        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String sql = "SELECT r.id,r.email,r.status FROM rentalApp r , property p WHERE p.agency_id = '" + email + "' AND r.status ='false'  AND p.id =r.id AND r.notif = '1' and p.rented = 'wait'";
        Cursor cursor = databaseHelper.getData(sql);
        if (cursor.getCount() != -1) {
            while (cursor.moveToNext()) {
                    Toast.makeText(this, "you have request from " + cursor.getString(1) , Toast.LENGTH_SHORT).show();
                    databaseHelper.updaterented(cursor.getString(0), email, "false", "2");
            }
        }else{
            Toast.makeText(this, "no have request" , Toast.LENGTH_SHORT).show();
        }
        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgencyHomePage.this, newAllpropirty.class);
                startActivity(intent);
            }
        });
    }

    public void post(View view) {
        Intent intent = new Intent(this, Post.class);
        startActivity(intent);
    }


    public void history(View view) {
        Intent intent = new Intent(this, RentalHistoryOfAgency.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(this, updateAgencyProfile.class);
        startActivity(intent);

    }

    public void logout(View view) {
        sessionManager.logoutUser();
    }

    public void tenant_view(View view) {
        Intent intent = new Intent(this,ViewTenant.class) ;
        startActivity(intent);
    }


}