package com.example.and;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.and.Model.Propirty;

import java.util.ArrayList;

public class searchResult extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Intent intent = getIntent();
        String smin = intent.getStringExtra("SurfaceMin");
        String smax = intent.getStringExtra("SurfaceMax");
        String bmin = intent.getStringExtra("BedMin");
        String bmax = intent.getStringExtra("BedMax");
        String pmax = intent.getStringExtra("MaxPrice");
        String sta = intent.getStringExtra("status");
        String loc = intent.getStringExtra("location");
        String garden = intent.getStringExtra("garden");
        String balcony = intent.getStringExtra("balcony");
        RecyclerView recycler = findViewById(R.id.serchrecycle);
        ArrayList<Propirty> propirties = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);

        Cursor cursor = databaseHelper.getData("SELECT * FROM property where rented ='false'");
        while (cursor.moveToNext()) {
            String id = cursor.getString( 0 ) ;
            String agency = cursor.getString(1);
            String city = cursor.getString(2);
            String postal = cursor.getString(3);
            String surface = cursor.getString(4);
            String year = cursor.getString(5);
            String bed = cursor.getString(6);
            String price = cursor.getString(7);
            String status = cursor.getString(8);
            String date = cursor.getString(10);
            String discription = cursor.getString(11);
            String garde = cursor.getString(12);
            String balcon = cursor.getString(13);
            propirties.add(new Propirty(id,agency, city, postal, surface, year, bed, price, status, date, discription, garde, balcon));
        }
        ArrayList<Propirty> serchList = new ArrayList<>();

        for (int i = 0; i < propirties.size(); i++) {
            if ((Double.parseDouble(propirties.get(i).getSurface_area()) > Double.parseDouble(smin) && Double.parseDouble(propirties.get(i).getSurface_area()) < Double.parseDouble(smax)) &&
                    (Integer.parseInt(propirties.get(i).getNumOfBed()) > Integer.parseInt(bmin) && Integer.parseInt(propirties.get(i).getNumOfBed()) > Integer.parseInt(bmax)) &&
                    Double.parseDouble(propirties.get(i).getRental_price()) < Double.parseDouble(pmax) &&
                    propirties.get(i).getCity().equals(loc) && propirties.get(i).getGarden().equals(garden) &&
                    propirties.get(i).getBalcony().equals(balcony)
            ) {
                serchList.add(propirties.get(i));
            }

        }
        recycler.setLayoutManager(new LinearLayoutManager(this));
        PropirtyAdabter adapter = new PropirtyAdabter(this, serchList);
        recycler.setAdapter(adapter);


    }
}