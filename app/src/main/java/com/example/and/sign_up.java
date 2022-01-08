package com.example.and;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void sign_up_tenant(View view) {
      Intent intent = new Intent(this, TenantSign_up.class) ;
      startActivity(intent);
    }

    public void sign_up_agency(View view) {
        Intent intent = new Intent(this,AgencySign_up.class) ;
        startActivity(intent);
    }


}