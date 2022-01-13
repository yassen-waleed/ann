package com.example.and;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.and.Model.TeneantHistory;

import java.util.ArrayList;

public class HistoryTenatOnRequest extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    RecyclerView historyRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_history_tenat_on_request);
        databaseHelper = new DatabaseHelper(this);
        historyRecycle = findViewById(R.id.history_recycle);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        Cursor cursor = databaseHelper.getData("SELECT p.agency_id, p.price,p.postalAddress, p.city,p.status FROM property p,rentalApp r WHERE r.email = '" + email + "' and r.status = 'true' ");
        ArrayList<TeneantHistory> histories = new ArrayList<>();
        while (cursor.moveToNext()) {
            String agencyemail = cursor.getString(0);
            String price = cursor.getString(1);
            String postal = cursor.getString(2);
            String city = cursor.getString(3);
            String status = cursor.getString(4);
            histories.add(new TeneantHistory(agencyemail, price, postal, city, status));
        }
        historyRecycle.setLayoutManager(new LinearLayoutManager(this));
        HistoryTenantAdabter adapter = new HistoryTenantAdabter(this, histories);
        historyRecycle.setAdapter(adapter);
    }
}