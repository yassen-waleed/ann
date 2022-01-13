package com.example.and;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.and.Model.Propirty;

public class detailspropertyAgency extends AppCompatActivity {
    TextView city, price, bed, garden, year, balcony,desc;
    DatabaseHelper databaseHelper;
    Button delete,edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_detailsproperty_agency);
        city = findViewById(R.id.detailscity_agency);
        price = findViewById(R.id.detailsprice_agency);
        bed = findViewById(R.id.detailsnumofbed_agency);
        balcony = findViewById(R.id.detailsbalconyy_agency);
        desc = findViewById(R.id.detailsdescription_agency) ;
        garden = findViewById(R.id.detailsgardenn_agency);
        year = findViewById(R.id.detailsyear_agency);
        delete = findViewById(R.id.delete);
        edit = findViewById(R.id.editprop_agency);
        Intent intent = getIntent();
        String id =intent.getStringExtra("id");
        String cityy = intent.getStringExtra("city");
        String pricee = intent.getStringExtra("price");
        String bedd = intent.getStringExtra("bed");
        String yearr = intent.getStringExtra("year");
        String gardenn = intent.getStringExtra("garden");
        String balconyy = intent.getStringExtra("balcony");
         String postall = intent.getStringExtra("postal") ;
        String description  =intent.getStringExtra("discription") ;
       String date = intent.getStringExtra("date") ;
        String agency = intent.getStringExtra("agency") ;
        String statuss = intent.getStringExtra("status") ;
        String surface = intent.getStringExtra("surface") ;
        city.setText("City: " + cityy);
        price.setText("Price: "+pricee);
        year.setText("Year: "+yearr);
        bed.setText("bedrooms: "+bedd);
        desc.setText(description);
        if (balconyy.equals("true")) {
            balcony.setText("has Balcony");
        } else {
            balcony.setText("hasnt Balcony");
        }
        if (gardenn.equals("true")) {
            garden.setText("has garden");
        } else {
            garden.setText("hasnt garden");
        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteProp(id);
                Intent intent1 = new Intent(detailspropertyAgency.this,newAllpropirty.class) ;
                startActivity(intent1);

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent2 = new Intent(detailspropertyAgency.this,EditPropertyForm.class) ;
                intent2.putExtra("id",id) ;
                intent2.putExtra("city",cityy) ;
                intent2.putExtra("garden",gardenn) ;
                intent2.putExtra("bed",bedd) ;
                intent2.putExtra("year",yearr) ;
                intent2.putExtra("price",pricee) ;
                intent2.putExtra("balcony",balconyy) ;
                intent2.putExtra("postal", postall) ;
                intent2.putExtra("discription",  description) ;
                intent2.putExtra("date",  date) ;
                intent2.putExtra("agency",  agency) ;
                intent2.putExtra("status", statuss) ;
                intent2.putExtra("surface",  surface) ;
                startActivity(intent2);
            }
        });

    }

    public void edit(View view) {

    }

}