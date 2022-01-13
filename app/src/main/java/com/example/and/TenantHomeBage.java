package com.example.and;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

public class TenantHomeBage extends AppCompatActivity {
    SessionManager session;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_home_bage);
        session = new SessionManager(this);
        databaseHelper = new DatabaseHelper(this);
        session.checkLogin();
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String sql = "SELECT r.id,p.agency_id,r.status FROM rentalApp r , property p WHERE r.email = '" + email + "'  AND p.id =r.id AND r.notif = '1'";
        Cursor cursor = databaseHelper.getData(sql);
        if (cursor.getCount() != -1) {
            while (cursor.moveToNext()) {
                if (cursor.getString(2).equals("true")) {
                    Toast.makeText(this, "your request approve from " + cursor.getString(2) + " for house number " + cursor.getString(0), Toast.LENGTH_SHORT).show();
                    databaseHelper.updaterented(cursor.getString(0), email, "true", "2");
                } else if (cursor.getString(2).equals("false")) {
                    databaseHelper.updaterented(cursor.getString(0), email, "false", "2");
                    Toast.makeText(this, "your request reject from " + cursor.getString(2) + " for house number " + cursor.getString(0), Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this, "no have notifications", Toast.LENGTH_SHORT).show();
        }
    }

    public void search(View view) {
        Intent intent = new Intent(this, search.class);
        startActivity(intent);
    }

    public void home(View view) {

    }

    public void history(View view) {
        Intent intent = new Intent(this, RentalHistoryOfTenant.class);
        startActivity(intent);
    }

    public void logout(View view) {
        session.logoutUser();
        Intent intent = new Intent(this, Sign_in.class);
        startActivity(intent);

    }

    public void main_page(View view) {
        Intent intent = new Intent(this, mainpage.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(this, EditTenantProfile.class);
        startActivity(intent);
    }
}