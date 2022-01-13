package com.example.and;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class EditPropertyForm extends AppCompatActivity {
    public EditText postal, surface, year, bed, price, date, description;
    Spinner status, city;
    CheckBox garden, balcony;
    Button update;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_property_form);
        city = findViewById(R.id.editprop_citytxt);
        postal = findViewById(R.id.editprop_postal);
        surface = findViewById(R.id.editprop_surface);
        year = findViewById(R.id.editprop_year);
        bed = findViewById(R.id.editprop_bed);
        price = findViewById(R.id.editprop_Maxprice);
        date = findViewById(R.id.editprop_date);
        description = findViewById(R.id.editprop_discription);
        status = findViewById(R.id.editprop_status);
        garden = findViewById(R.id.editprop_gardencheck);
        balcony = findViewById(R.id.editprop_balconycheck);
        update = findViewById(R.id.editprop_btn);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String cityy = intent.getStringExtra("city");
        String pricee = intent.getStringExtra("price");
        String bedd = intent.getStringExtra("bed");
        String yearr = intent.getStringExtra("year");
        String gardenn = intent.getStringExtra("garden");
        String balconyy = intent.getStringExtra("balcony");
        String postall = intent.getStringExtra("postal");
        String discription = intent.getStringExtra("discription");
        String datee = intent.getStringExtra("date");
        String agencyy = intent.getStringExtra("agency");
        String statuss = intent.getStringExtra("status");
        String surfacee = intent.getStringExtra("surface");

        databaseHelper = new DatabaseHelper(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_city, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(adapter);
        if (cityy != null) {
            int spinnerPosition = adapter.getPosition(cityy);
            city.setSelection(spinnerPosition);
        }

        price.setText(pricee);
        bed.setText(bedd);
        year.setText(yearr);
        postal.setText(postall);
        description.setText(discription);
        date.setText(datee);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.array_status, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapter1);
        if (statuss != null) {
            int spinnerPosition = adapter.getPosition(statuss);
            status.setSelection(spinnerPosition);
        }
        surface.setText(surfacee);

        if (gardenn.equals("true")) {
            garden.setChecked(true);
        } else {
            garden.setChecked(false);
        }

        if (balconyy.equals("true")) {
            balcony.setChecked(true);
        } else {
            balcony.setChecked(false);
        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityyy = city.getSelectedItem().toString();
                String p = price.getText().toString();
                String bbed = bed.getText().toString();
                String yyear = year.getText().toString();
                String pos = postal.getText().toString();
                String desc = description.getText().toString();
                String dattte = date.getText().toString();
                String s = status.getSelectedItem().toString();
                String sur = surface.getText().toString();
                String gg = "";
                if (garden.isChecked()) {
                    gg = "true";
                } else {
                    gg = "false";
                }
                String bb = "";
                if (balcony.isChecked()) {
                    bb = "true";

                } else {
                    bb = "false";
                }
                databaseHelper.updateProperty(id, agencyy, cityyy, pos, sur, yyear, bbed, p, s, dattte, desc, gg, bb);
                Intent intent1 = new Intent(EditPropertyForm.this, newAllpropirty.class);
                startActivity(intent1);
            }
        });
    }
}