package com.example.and;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class GuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
    }

    public void sign_in(View view) {
        Intent intent=new Intent(this, Sign_in.class) ;
        startActivity(intent);
    }

    public void sign_up(View view) {
        Intent intent=new Intent(this, sign_up.class) ;
        startActivity(intent);
    }

    public void Search(View view) {
        Intent intent=new Intent(this,search.class) ;
        startActivity(intent);
    }
}