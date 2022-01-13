package com.example.and;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


public class AgencySign_up extends AppCompatActivity {
    public EditText email, agencyName, pass, confirm, phone;
    Spinner country, city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_sign_up);
        email = findViewById(R.id.emailtxt);
        agencyName = findViewById(R.id.nametxt);
        pass = findViewById(R.id.passtxt);
        confirm = findViewById(R.id.confirmtxt);
        country = findViewById(R.id.countrytxt);
        city = findViewById(R.id.citytxt);
        phone = findViewById(R.id.phone_number);
       country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               addIntro(country.getSelectedItem().toString());
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
    }


    public void sign(View view) {
        DatabaseHelper db = new DatabaseHelper(this);
        String em = email.getText().toString().trim();
        String name = agencyName.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String conf = confirm.getText().toString().trim();
        String count = country.getSelectedItem().toString().trim();
        String ci = city.getSelectedItem().toString().trim();
        String ph = phone.getText().toString().trim();

        if (!validateEmail(em) | !checkname(name) | !checkpassword(password) | !checkconfirm(password, conf) | !checkphone(ph))
            return;

        db.addAgency(
                em, name, password, conf,ph, count, ci
        );
        Intent intent = new Intent(this, Sign_in.class);
        startActivity(intent);

    }

    boolean validateEmail(String emaill) {
        String pattren = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (emaill.isEmpty()) {
            email.setError("is Empty");
            return false;
        } else if (!emaill.matches(pattren)) {
            email.setError("not valid email");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    public boolean checkname(String name) {
        if (name.length() >= 20) {
            agencyName.setError("name to long");
            return false;
        } else if (name.isEmpty()) {
            agencyName.setError("is Empty");
            return false;
        } else {
            agencyName.setError(null);
            return true;
        }
    }

    public boolean checkpassword(String password) {
        String p = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" ;
        if (password.isEmpty()) {
            pass.setError("is Empty");
            return false;
        } else if (!password.matches(p)) {
            pass.setError("weak password");
            return false;
        } else {
            pass.setError(null);
            return true;
        }
    }

    public boolean checkphone(String str) {
        if (str.isEmpty()) {
            phone.setError("is Empty");
            return false;
        } else {
            phone.setError(null);
            return true;
        }
    }

    public boolean checkconfirm(String pass, String conf) {
        if (conf.isEmpty()) {
            confirm.setError("is Empty");
            return false;
        } else if (!conf.equals(pass)) {
            confirm.setError("not matches");
            return false;
        } else {
            confirm.setError(null);
            return true;
        }
    }

    public void addIntro(String countryname) {
        if (countryname.equals("palestine")) {
            phone.setText("+972");
        }else if(countryname.equals("Russia")){
            phone.setText("+7");
        }else if(countryname.equals("jordan")){
            phone.setText("+962");
        }else if(countryname.equals("Egypt")){
            phone.setText("+20");
        }else if(countryname.equals("England")){
            phone.setText("+44");
        }else if(countryname.equals("syria")){
            phone.setText("+963");
        }
    }

}