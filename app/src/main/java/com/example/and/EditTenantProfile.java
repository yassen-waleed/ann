package com.example.and;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;

public class EditTenantProfile extends AppCompatActivity {
    EditText oldpassword, firstName, lastname, pass, confirm, monthlySalary, occ, familySize, phone;
    Spinner gender, country, city, nati;
    DatabaseHelper databaseHelper;
    SessionManager session ;
    private static String password= "" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tenant_profile);
        session = new SessionManager(this);
        session.checkLogin();
        databaseHelper = new DatabaseHelper(this);

        HashMap<String, String> user = session.getUserDetails();
        String emaill = user.get(SessionManager.KEY_EMAIL);

        gender = findViewById(R.id.GenderspinnerTP);
        oldpassword = findViewById(R.id.oldpasswordtxtTP);
        firstName = findViewById(R.id.firsttxtTP);
        lastname = findViewById(R.id.lasttxtTP);
        pass = findViewById(R.id.passtxtTP);
        confirm = findViewById(R.id.confirmtxtTP);
        nati = findViewById(R.id.nationalitytxtTP);
        monthlySalary = findViewById(R.id.salaryTP);
        occ = findViewById(R.id.occupationtxtTP);
        familySize = findViewById(R.id.familysizetxtTP);
        phone = findViewById(R.id.phoneTP);
        country = findViewById(R.id.countrytxtTP);
        city = findViewById(R.id.citytxtTP);
        String ci = "" ;
        String nal = "" ;
        String genn = "" ;
        String coun = "" ;



        Cursor cursor = databaseHelper.getData("Select * from tenant where email = '" + emaill + "'");
        while (cursor.moveToNext()) {
            firstName.setText(cursor.getString(1));
            lastname.setText(cursor.getString(2));
            monthlySalary.setText(cursor.getString(7));
            occ.setText(cursor.getString(8));
            familySize.setText(cursor.getString(9));
            phone.setText(cursor.getString(10));
            ci = cursor.getString(12);
            coun = cursor.getString(11);
            genn = cursor.getString(5);
            nal = cursor.getString(6);
            password = cursor.getString(3) ;

        }
        databaseHelper = new DatabaseHelper(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_city, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(adapter);
        if (ci != null) {
            int spinnerPosition = adapter.getPosition(ci);
            city.setSelection(spinnerPosition);
        }


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.array_country, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(adapter1);
        if (coun != null) {
            int spinnerPosition = adapter1.getPosition(coun);
            city.setSelection(spinnerPosition);
        }

        /* *********************************************** */

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.array_gender, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter2);
        if (genn != null) {
            int spinnerPosition = adapter2.getPosition(genn);
            city.setSelection(spinnerPosition);
        }

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.array_nati, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nati.setAdapter(adapter3);
        if (nal != null) {
            int spinnerPosition = adapter3.getPosition(nal);
            city.setSelection(spinnerPosition);
        }


    }

    public void update(View view) {
        session = new SessionManager(this);
        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        String emaill = user.get(SessionManager.KEY_EMAIL);
        DatabaseHelper db = new DatabaseHelper(this);
        String first = firstName.getText().toString().trim();
        String last = lastname.getText().toString().trim();
        String oldpasswordd= oldpassword.getText().toString().trim();
        String passwordd = pass.getText().toString().trim();
        String conf = confirm.getText().toString().trim();
        String gen = gender.getSelectedItem().toString().trim();
        String nat = nati.getSelectedItem().toString().trim();
        String salary = monthlySalary.getText().toString().trim();
        String occup = occ.getText().toString().trim();
        String family = familySize.getText().toString().trim();
        String ph = phone.getText().toString().trim();
        String cont = country.getSelectedItem().toString().trim();
        String ci = city.getSelectedItem().toString().trim();
        if(oldpasswordd.isEmpty()){
            if (  !checkfirstname(first) | !checksalary(salary) | !checkfamily(family) |
                    !checklastname(last) | !checkoccuption(occup) | !checkphone(ph))
                return;
            databaseHelper.updateTenant(emaill,first,last,password,password,gen,nat,salary,occup,family,ph,cont,ci) ;
        }else if(!oldpasswordd.isEmpty()){
            if (  !checkfirstname(first) | !checksalary(salary) | !checkfamily(family) |
                    !checklastname(last) | !checkpassword(passwordd) | !checkconfirm(passwordd, conf)
                    | !checkoccuption(occup) | !checkphone(ph) |checkOld(emaill,oldpasswordd))
                return;
            databaseHelper.updateTenant(emaill,first,last,passwordd,conf,gen,nat,salary,occup,family,ph,cont,ci) ;
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


    public boolean checkfirstname(String name) {
        if (name.length() >= 20) {
            firstName.setError("name to long");
            return false;
        } else if (name.length() < 3) {
            firstName.setError("name to small");
            return false;
        } else if (name.isEmpty()) {
            firstName.setError("is Empty");
            return false;
        } else {
            firstName.setError(null);
            return true;
        }
    }

    public boolean checklastname(String name) {
        if (name.length() >= 20) {
            lastname.setError("name to long");
            return false;
        } else if (name.length() < 3) {
            lastname.setError("name to small");
            return false;
        } else if (name.isEmpty()) {
            lastname.setError("is Empty");
            return false;
        } else {
            lastname.setError(null);
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

    public boolean checkoccuption(String occup) {
        if (occup.length() >= 20) {
            occ.setError("name to long");
            return false;
        } else if (occup.isEmpty()) {
            occ.setError("is Empty");
            return false;
        } else {
            occ.setError(null);
            return true;
        }
    }

    public boolean checksalary(String salaryy) {
        if (salaryy.isEmpty()) {
            monthlySalary.setError("is Empty");
            return false;
        } else {
            monthlySalary.setError(null);
            return true;
        }
    }

    public boolean checkfamily(String fa) {
        if (fa.isEmpty()) {
            familySize.setError("is Empty");
            return false;
        } else {
            familySize.setError(null);
            return true;
        }
    }
    public boolean checkOld(String email, String old) {
        if (!databaseHelper.checkusernamepasswordAgency(email, old)) {
            oldpassword.setError("Old password is not true");
            return false;
        } else {
            pass.setError(null);
            return true;
        }

    }

}