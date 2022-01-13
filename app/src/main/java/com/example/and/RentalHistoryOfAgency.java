package com.example.and;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.and.Model.AgencyHistory;
import com.example.and.Model.TeneantHistory;

import java.util.ArrayList;
import java.util.HashMap;

public class RentalHistoryOfAgency extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    RecyclerView historyRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rental_history_of_agency);
        databaseHelper = new DatabaseHelper(this);
        historyRecycle = findViewById(R.id.agencyrecyclhistory);
        Intent intent = getIntent();
        SessionManager sessionManager = new SessionManager(this) ;
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);
        Cursor cursor = databaseHelper.getData("SELECT p.id, t.first_name,t.last_name,t.email,p.postalAddress, p.city,p.status,r.startdate, r.enddate \n" +
                "FROM tenant t,property p,rentalApp r " +
                "WHERE r.email = t.email and r.status = 'true' and r.id = p.id and p.agency_id = '"+email+"'");
        ArrayList<AgencyHistory> histories = new ArrayList<>();
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String first_name = cursor.getString(1);
            String last_name = cursor.getString(2);
            String tenantemail = cursor.getString(3);
            String postal = cursor.getString(4);
            String city = cursor.getString(5);
            String status = cursor.getString(6);
            String start = cursor.getString(7) ;
            String end = cursor.getString(8) ;
            histories.add(new AgencyHistory(tenantemail,first_name,last_name, id, postal, city, status,start,end));
        }
        historyRecycle.setLayoutManager(new LinearLayoutManager(this));
        AgencyHistoryAdabter adapter = new AgencyHistoryAdabter(this, histories);
        historyRecycle.setAdapter(adapter);
    }
}