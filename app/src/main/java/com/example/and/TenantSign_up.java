package com.example.and;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class TenantSign_up extends AppCompatActivity {
    EditText email, firstName, lastname, pass, confirm, monthlySalary, occ, familySize, phone;
    Spinner gender, country, city, nati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_sign_up);
        gender = findViewById(R.id.Genderspinner);
        email = findViewById(R.id.emailtxt);
        firstName = findViewById(R.id.firsttxt);
        lastname = findViewById(R.id.lasttxt);
        pass = findViewById(R.id.passtxt);
        confirm = findViewById(R.id.confirmtxt);
        nati = findViewById(R.id.nationalitytxt);
        monthlySalary = findViewById(R.id.salary);
        occ = findViewById(R.id.occupationtxt);
        familySize = findViewById(R.id.familysizetxt);
        phone = findViewById(R.id.phone);
        country = findViewById(R.id.countrytxt);
        city = findViewById(R.id.citytxt);

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

    public void sign_up(View view) {
        DatabaseHelper db = new DatabaseHelper(TenantSign_up.this);
        String em = email.getText().toString().trim();
        String first = firstName.getText().toString().trim();
        String last = lastname.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String conf = confirm.getText().toString().trim();
        String gen = gender.getSelectedItem().toString().trim();
        String nat = nati.getSelectedItem().toString().trim();
        String salary = monthlySalary.getText().toString().trim();
        String occup = occ.getText().toString().trim();
        String family = familySize.getText().toString().trim();
        String ph = phone.getText().toString().trim();
        String cont = country.getSelectedItem().toString().trim();
        String ci = city.getSelectedItem().toString().trim();
        if (!validateEmail(em) | !checkfirstname(first) | !checksalary(salary) | !checkfamily(family) |
                !checklastname(last) | !checkpassword(password) | !checkconfirm(password, conf)
                | !checkoccuption(occup) | !checkphone(ph)) {
            return;
        }
        db.addTenant(em, first, last, password, conf, gen, nat, salary, occup, family, ph, cont, ci);

        Intent intent = new Intent(this, Sign_in.class);
        startActivity(intent);
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
}