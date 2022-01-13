package com.example.and;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.and.Model.Propirty;
import com.example.and.Model.Rented;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewTenant extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tenant);
        recyclerView = findViewById(R.id.ViewTenantRecycle);
        ArrayList<Rented> renteds = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);
        Cursor cursor = databaseHelper.getData("SELECT  p.id,p.agency_id,t.email,p.postalAddress,t.first_name,last_name,r.status,t.monthlySalary,t.familySize,t.phone" +
                " FROM property p,rentalApp r,tenant t" +
                " where r.status = 'false' AND  p.agency_id = '" + email+"' and p.id = r.id and t.email = r.email"  );
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String emaill = cursor.getString(1);
            String Temail = cursor.getString(2);
            String postall = cursor.getString(3);
            String first = cursor.getString(4);
            String last = cursor.getString(5);
            String statuss = cursor.getString(6);
            String salary = cursor.getString(7) ;
            String family = cursor.getString(8) ;
            String phone = cursor.getString(9) ;

            renteds.add(new Rented(id, postall,Temail, statuss, first, last, emaill,salary,family,phone));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ViewTenantAdabter adapter = new ViewTenantAdabter(this, renteds);
        recyclerView.setAdapter(adapter);

    }
}