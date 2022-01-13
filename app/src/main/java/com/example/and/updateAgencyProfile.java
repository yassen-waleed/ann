package com.example.and;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.and.Model.Propirty;

import java.util.HashMap;

public class updateAgencyProfile extends AppCompatActivity {
    SessionManager session;
    public EditText email, agencyName, oldpass, pass, confirm, phone;
    DatabaseHelper databaseHelper;
    Spinner country, city;
    String oldpasswordd = "" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_agency_profile);
        session = new SessionManager(this);
        session.checkLogin();
        databaseHelper = new DatabaseHelper(this);

        HashMap<String, String> user = session.getUserDetails();
        String emaill = user.get(SessionManager.KEY_EMAIL);

        email = findViewById(R.id.agency_emailtxt);
        agencyName = findViewById(R.id.agency_nametxt);
        oldpass = findViewById(R.id.agency_Oldpasstxt);
        pass = findViewById(R.id.agency_passtxt);
        confirm = findViewById(R.id.agency_confirmtxt);
        country = findViewById(R.id.agency_countrytxt);
        city = findViewById(R.id.agency_citytxt);
        phone = findViewById(R.id.agency_phone_number);


        Cursor cursor = databaseHelper.getData("Select * from agency where email = '" + emaill + "'");

        while (cursor.moveToNext()) {
            email.setText(cursor.getString(0));
            agencyName.setText(cursor.getString(1));
            phone.setText(cursor.getString(4));
            oldpasswordd = cursor.getString(2) ;
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

    public boolean checkOld(String email, String old) {
        if (!databaseHelper.checkusernamepasswordAgency(email, old)) {
            oldpass.setError("Old password is not true");
            return false;
        } else {
            pass.setError(null);
            return true;
        }

    }

    public boolean checkpassword(String password) {
        String p = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
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
        } else if (countryname.equals("Russia")) {
            phone.setText("+7");
        } else if (countryname.equals("jordan")) {
            phone.setText("+962");
        } else if (countryname.equals("Egypt")) {
            phone.setText("+20");
        } else if (countryname.equals("England")) {
            phone.setText("+44");
        } else if (countryname.equals("syria")) {
            phone.setText("+963");
        }
    }

    public void update(View view) {

        DatabaseHelper db = new DatabaseHelper(this);
        String name = agencyName.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String oldpassword = oldpass.getText().toString().trim();
        String conf = confirm.getText().toString().trim();
        String count = country.getSelectedItem().toString().trim();
        String ci = city.getSelectedItem().toString().trim();
        String ph = phone.getText().toString().trim();
        session = new SessionManager(this);
        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        String emaill = user.get(SessionManager.KEY_EMAIL);
        if (oldpassword.isEmpty()) {
            if (!checkname(name) | !checkphone(ph))
                return;
            databaseHelper.updateAgency(emaill, name, oldpasswordd, oldpasswordd, ph, count, ci);


        } else if(!oldpassword.isEmpty()) {
            if (!checkname(name) | checkOld(emaill, oldpassword) | !checkpassword(password) | !checkconfirm(password, conf) | !checkphone(ph))
                return;

            databaseHelper.updateAgency(emaill, name, password, conf, ph, count, ci);
        }
    }
}