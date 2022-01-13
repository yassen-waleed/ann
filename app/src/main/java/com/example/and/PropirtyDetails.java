package com.example.and;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;

public class PropirtyDetails extends AppCompatActivity {
    TextView city, price, bed, garden, year, balcony, description;
    Button apply,startdate,enddate;
    SessionManager sessionManager ;
    DatabaseHelper db ;
    private DatePickerDialog datePickerDialog  ;
    private DatePickerDialog datePickerDialog1  ;
    private static String start="" ;
    private static String end="" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propirty_details);
        sessionManager = new SessionManager(this)  ;
        sessionManager.checkLogin();
       intiDatePicker() ;
        intiDatePicker1() ;
        db = new DatabaseHelper(this) ;
        description = findViewById(R.id.detailsdescription);
        city = findViewById(R.id.detailscity);
        price = findViewById(R.id.detailsprice);
        bed = findViewById(R.id.detailsnumofbed);
        balcony = findViewById(R.id.detailsbalconyy);
        garden = findViewById(R.id.detailsgardenn);
        year = findViewById(R.id.detailsyear);
        apply = findViewById(R.id.apply) ;
        startdate=findViewById(R.id.startdate) ;
        enddate=findViewById(R.id.enddate) ;
        Intent intent = getIntent();
        String id =intent.getStringExtra("id");
        String cityy = intent.getStringExtra("city");
        String pricee = intent.getStringExtra("price");
        String bedd = intent.getStringExtra("bed");
        String yearr = intent.getStringExtra("year");
        String gardenn = intent.getStringExtra("garden");
        String balconyy = intent.getStringExtra("balcony");
        String postall = intent.getStringExtra("postal") ;
        String descriptionn  =intent.getStringExtra("discription") ;
        String date = intent.getStringExtra("date") ;
        String agency = intent.getStringExtra("agency") ;
        String statuss = intent.getStringExtra("status") ;
        String surface = intent.getStringExtra("surface") ;
        city.setText("City " + cityy);
        price.setText(pricee);
        year.setText(yearr);
        bed.setText(bedd);
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
        description.setText(descriptionn);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 ;
                if(!sessionManager.isLoggedIn()){
                    intent1 = new Intent(PropirtyDetails.this,Sign_in.class)  ;
                }else {
                    if(start.isEmpty() | end.isEmpty()){
                        Toast.makeText(PropirtyDetails.this, "start or end date empty", Toast.LENGTH_SHORT).show();
                    }else {
                        sessionManager.checkLogin();
                        HashMap<String, String> user = sessionManager.getUserDetails();
                        String email = user.get(SessionManager.KEY_EMAIL);
                        db.addrentapp(id,email,"false",start,end,"1");
                        db.updateRentedStatus(id,"wait");
                        intent1 = new Intent(PropirtyDetails.this,TenantHomeBage.class)  ;
                    }
                   
                }
            }
        });
    }

    private void intiDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1 ;
                 start = makeDateString(day,month,year) ;
                startdate.setText(start);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }
    private void intiDatePicker1() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1 ;
                 end = makeDateString(day,month,year) ;
                enddate.setHint(end);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog1 = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getmonthformate(month)+" "+day+" "+year ;
    }

    private String getmonthformate(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void opendatepicker(View view) {
        datePickerDialog.show();
    }

    public void opendatepicker1(View view) {
        datePickerDialog1.show();
    }
}