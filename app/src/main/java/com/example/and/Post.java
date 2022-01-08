package com.example.and;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class Post extends AppCompatActivity {
    public EditText city, postal, surface, year, bed, price, date, description;
    public DatabaseHelper databaseHelper;
    Spinner status;
    private Bitmap bitmap;
    CheckBox garden, balcony;
    SessionManager session;
    final int REQUEST_CODE_GALLERY = 100;
    ImageView imageView;
    private Uri imagefilepath;
   private static int i = 1 ;
    private Bitmap imagetostore ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        city = findViewById(R.id.citytxt);
        postal = findViewById(R.id.postal);
        surface = findViewById(R.id.surface);

        year = findViewById(R.id.year);
        bed = findViewById(R.id.bed);
        price = findViewById(R.id.price);

        date = findViewById(R.id.date);
        description = findViewById(R.id.discription);
        status = findViewById(R.id.status);
        garden = findViewById(R.id.garden);
        balcony = findViewById(R.id.balcony);
        imageView = findViewById(R.id.imageView);
    }

    public void post(View view) {
        String ci = city.getText().toString();
        String pos = postal.getText().toString();
        String surfaceArea = surface.getText().toString();
        String yearr = year.getText().toString();
        String bedrooms = bed.getText().toString();
        String Rprice = price.getText().toString();
        String datee = date.getText().toString();
        String descriptionn = description.getText().toString();
        String s = status.getSelectedItem().toString();
        session = new SessionManager(this);
        session.checkLogin();
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String gg = "";
        if (garden.isChecked())
            gg = "true";
        else
            gg = "false";

        String bb = "";
        if (balcony.isChecked())
            bb = "true";
        else
            bb = "false";

            if(imageView.getDrawable()!=null && imagetostore!=null) {
                databaseHelper.addProperty(email,ci,pos,surfaceArea,yearr,bedrooms,Rprice,s,imagetostore,datee,descriptionn,gg,bb);
            }
    }

    public void Choose(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null && data.getData() != null ) {
                imagefilepath = data.getData();
                imagetostore  = MediaStore.Images.Media.getBitmap(getContentResolver(),imagefilepath) ;
            }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void storeImage(View view)  {

    }
}