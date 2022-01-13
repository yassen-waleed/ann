package com.example.and;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.and.Model.TeneantHistory;

import java.util.ArrayList;
import java.util.HashMap;

public class RentalHistoryOfTenant extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    RecyclerView historyRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rental_history_of_tenant);
        databaseHelper = new DatabaseHelper(this);
        historyRecycle = findViewById(R.id.tenantHistoryRecycle);
        Intent intent = getIntent();
        SessionManager sessionManager = new SessionManager(this) ;
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);
        Cursor cursor = databaseHelper.getData("SELECT  p.agency_id, p.price,p.postalAddress, p.city,p.status, r.startdate, r.enddate FROM property p,rentalApp r WHERE r.email = '" + email + "' and r.status = 'true' and r.id = p.id ");
        ArrayList<TeneantHistory> histories = new ArrayList<>();
        while (cursor.moveToNext()) {
            String agencyemail = cursor.getString(0);
            String price = cursor.getString(1);
            String postal = cursor.getString(2);
            String city = cursor.getString(3);
            String status = cursor.getString(4);
            String start = cursor.getString(5);
            String end = cursor.getString(6);
            histories.add(new TeneantHistory(agencyemail, price, postal, city, status,start,end));
        }
        historyRecycle.setLayoutManager(new LinearLayoutManager(this));
        HistoryTenantAdabter adapter = new HistoryTenantAdabter(this, histories);
        historyRecycle.setAdapter(adapter);
    }
}