package com.example.and;

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
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class Post extends AppCompatActivity {
    public EditText postal, surface, year, bed, price, date, description;
    byte[] image;
    Spinner status, city;
    Button photo, post;
    CheckBox garden, balcony;
    SessionManager session;
    ImageView imageView;
    private static final int SELECT_PICTURE = 100;
    private int REQUEST_CODE_GALLERY = 999;
    public static DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        city = findViewById(R.id.citytxt);
        postal = findViewById(R.id.postal);
        surface = findViewById(R.id.surface);
        post = findViewById(R.id.post);
        year = findViewById(R.id.year);
        bed = findViewById(R.id.bed);
        price = findViewById(R.id.Maxprice);
        photo = findViewById(R.id.photo);
        date = findViewById(R.id.date);
        description = findViewById(R.id.discription);
        status = findViewById(R.id.status);
        garden = findViewById(R.id.gardencheck);
        balcony = findViewById(R.id.balconycheck);
        imageView = findViewById(R.id.imageView);
        databaseHelper = new DatabaseHelper(this);
        session = new SessionManager(this);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        Post.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);

            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ci = city.getSelectedItem().toString();
                String pos = postal.getText().toString();
                String surfaceArea = surface.getText().toString();
                String yearr = year.getText().toString();
                String bedrooms = bed.getText().toString();
                String Rprice = price.getText().toString();
                String datee = date.getText().toString();
                String descriptionn = description.getText().toString();
                String s = status.getSelectedItem().toString();
                session.checkLogin();
                HashMap<String, String> user = session.getUserDetails();
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


                try {

                    databaseHelper.addProperty(email,
                            ci, pos, surfaceArea, yearr, bedrooms, Rprice, s, image, datee, descriptionn, gg, bb, "false");

                } catch (Exception e) {
                    Toast.makeText(Post.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private byte[] imagetoByte() {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageInByte = baos.toByteArray();
        return imageInByte;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(this, "you need permission to access", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_GALLERY) {
                Uri uri = data.getData();
                if (uri != null) {
                    if (saveImageInDatabase(uri)) {
                        Toast.makeText(Post.this, "image save in database ", Toast.LENGTH_SHORT).show();
                        imageView.setImageURI(uri);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setVisibility(View.GONE);

                            }
                        }, 1000);
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (imageloadFromDB()) {
                                imageView.setVisibility(View.VISIBLE);
                                Toast.makeText(Post.this, "image load from database", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, 1000);
                }
            }

            super.onActivityResult(requestCode, resultCode, data);
        }


    }

    private boolean saveImageInDatabase(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            image = Utils.getBytes(inputStream);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private boolean imageloadFromDB() {
        try {
            byte[] bytes = databaseHelper.reterveImage() ;
            imageView.setImageBitmap(Utils.getImage(bytes));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}