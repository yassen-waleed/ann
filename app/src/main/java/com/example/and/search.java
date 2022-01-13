package com.example.and;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class search extends AppCompatActivity {
    EditText surfaceMin, SurfaceMax, bedMin, bedMax, MaxmumPrice;
    Spinner location, status;
    CheckBox garden, balcony;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        surfaceMin = findViewById(R.id.minSurface);
        SurfaceMax = findViewById(R.id.max_surface);
        bedMin = findViewById(R.id.minBed);
        bedMax = findViewById(R.id.maxBed);
        MaxmumPrice = findViewById(R.id.Maxprice);
        status = findViewById(R.id.status_spinner);
        location = findViewById(R.id.location_spinner);
        garden = findViewById(R.id.gardencheck);
        balcony = findViewById(R.id.balconycheck);


    }

    public void search(View view) {
        Intent intent = new Intent(this, searchResult.class);
        String smin = surfaceMin.getText().toString();
        String smax = SurfaceMax.getText().toString();
        String bmin = bedMin.getText().toString();
        String bmax = bedMax.getText().toString();
        String pmax = MaxmumPrice.getText().toString();
        String sta = status.getSelectedItem().toString();
        String loc = location.getSelectedItem().toString();

        String gard = "";
        if (garden.isChecked())
           gard = "true";
        else
            gard = "false";

        String bal = "";
        if (balcony.isChecked())
            bal = "true";
        else
            bal = "false";

        intent.putExtra("SurfaceMin",smin) ;
        intent.putExtra("SurfaceMax",smax) ;
        intent.putExtra("BedMin",bmax) ;
        intent.putExtra("BedMax",bmax) ;
        intent.putExtra("MaxPrice",pmax) ;
        intent.putExtra("location",loc) ;
        intent.putExtra("status",sta) ;
        intent.putExtra("garden",gard) ;
        intent.putExtra("balcony",bal) ;


        startActivity(intent);
    }
}