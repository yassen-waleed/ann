package com.example.and;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.and.Model.Propirty;

import java.util.ArrayList;
import java.util.HashMap;

public class mainpage extends AppCompatActivity {

    DatabaseHelper databaseHelper ;
    SessionManager sessionManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        RecyclerView recycler = findViewById(R.id.maunrecycle);
        ArrayList<Propirty> propirties = new ArrayList<>() ;
        databaseHelper = new DatabaseHelper(this) ;
        sessionManager = new SessionManager(this) ;
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);
        Cursor cursor  = databaseHelper.getData("SELECT * FROM property p " +
                "WHERE rented='false'" +
                "  ORDER BY ID DESC LIMIT 5") ;
        while(cursor.moveToNext()){
            String id  = cursor.getString( 0)  ;
            String agency  = cursor.getString( 1)  ;
            String city  = cursor.getString( 2)  ;
            String postal  = cursor.getString( 3)  ;
            String  surface = cursor.getString( 4)  ;
            String year  = cursor.getString( 5)  ;
            String bed  = cursor.getString( 6)  ;
            String price  = cursor.getString( 7)  ;
            String status  = cursor.getString( 8)  ;
            String  date = cursor.getString( 10)  ;
            String discription  = cursor.getString( 11)  ;
            String garden   = cursor.getString( 12)  ;
            String balcony  = cursor.getString( 13)  ;
            propirties.add(new Propirty(id,agency,city,postal,surface,year,bed,price,status,date,discription,garden,balcony)) ;
        }

        recycler.setLayoutManager(new LinearLayoutManager(this));
        PropirtyAdabter adapter = new PropirtyAdabter(this,propirties);
        recycler.setAdapter(adapter);






    }
}