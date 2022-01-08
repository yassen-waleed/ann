package com.example.and;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AgencyHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_home_page);
    }

    public void post(View view) {
        Intent intent = new Intent(this,Post.class) ;
        startActivity(intent);
    }

    public void mange(View view) {

    }

    public void history(View view) {
    }
}