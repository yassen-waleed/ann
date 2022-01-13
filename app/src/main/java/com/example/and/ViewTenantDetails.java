package com.example.and;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.and.Model.Tenant;

import java.util.ArrayList;

public class ViewTenantDetails extends AppCompatActivity {
    TextView name, email, familysiz, salary, phone;
    DatabaseHelper databaseHelper;
    Button history, reject, approve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tenant_details);
        name = findViewById(R.id.namevdtxt);
        email = findViewById(R.id.emailvdtxt);
        familysiz = findViewById(R.id.familysizevttxt);
        salary = findViewById(R.id.salaryyy);
        phone = findViewById(R.id.phonevdtxt);
        history = findViewById(R.id.history);
        approve = findViewById(R.id.approve);
        reject = findViewById(R.id.reject);
        databaseHelper = new DatabaseHelper(this) ;
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String fn = intent.getStringExtra("first");
        String ln = intent.getStringExtra("last");
        String em = intent.getStringExtra("email");
        String sal = intent.getStringExtra("salary");
        String fam = intent.getStringExtra("family");
        String pho = intent.getStringExtra("phone");

        name.setText(fn + " " + ln);
        email.setText("Email: " + em);
        salary.setText("Monthly Salary: " + sal);
        familysiz.setText("Family Size: " + fam);
        phone.setText("Phone Number: " + pho);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent1 = new Intent(ViewTenantDetails.this , HistoryTenatOnRequest.class) ;
               intent1.putExtra("email",em) ;
               startActivity(intent1);
            }
        });
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            databaseHelper.updaterented(id,em,"true","1");
            databaseHelper.updateRentedStatus(id,"true");
            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.updaterented(id,em,"false","1");
                databaseHelper.updateRentedStatus(id,"false");
            }
        });
    }
}