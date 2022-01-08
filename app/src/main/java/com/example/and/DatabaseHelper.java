package com.example.and;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    public Context context;
    public static final String DATABASE_NAME = "HouseRental";
    public static final int DATABASE_VERSION = 1;
    private ByteArrayOutputStream byteArrayOutputStream ;
    private byte[] imageInbytes;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
  static  int i= 1 ;
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE agency (" +
                "email TEXT PRIMARY KEY , " +
                "name TEXT, " +
                "password TEXT , " +
                "confirm TEXT ," +
                "phone TEXT , " +
                "country TEXT , " +
                " city TEXT  " +
                ") ;  ";
        String query1 = "CREATE TABLE tenant (" +
                "email TEXT PRIMARY KEY , " +
                "first_name TEXT," +
                "last_name TEXT," +
                "password TEXT , " +
                "confirm TEXT ," +
                "gender TEXT," +
                "nationality TEXT," +
                "monthlySalary TEXT," +
                "occupation TEXT," +
                "familySize TEXT," +
                "phone TEXT , " +
                "country TEXT , " +
                "city TEXT  " +
                ") ;  ";
        String query2 = "CREATE TABLE property (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "agency_id TEXT, " +
                "city TEXT," +
                "postalAddress TEXT," +
                "surfaceArea TEXT," +
                "constructionYear TEXT , " +
                "numOfBed TEXT ," +
                "price TEXT," +
                "status TEXT," +
                " photo BLOB," +
                "availability_date TEXT," +
                "description TEXT ," +
                " garden TEXT,"
                +"balcony TEXT,"+
                "FOREIGN KEY (agency_id) REFERENCES agency (email) ON DELETE CASCADE " +
                ") ;  ";

        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query1);
        sqLiteDatabase.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void addTenant(String email, String first_name, String last_name, String password,
                   String confirm, String gender, String nationality, String monthlySalary, String occupation,
                   String familysize, String phone, String country, String city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("first_name", first_name);
        cv.put("last_name", last_name);
        cv.put("password", password);
        cv.put("confirm", confirm);
        cv.put("gender", gender);
        cv.put("nationality", nationality);
        cv.put("monthlySalary", monthlySalary);
        cv.put("occupation", occupation);
        cv.put("familySize", familysize);
        cv.put("phone", phone);
        cv.put("country",country);
        cv.put("city",city);
        long res = db.insert("tenant",null,cv)  ;
        if(res==-1){
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    void addProperty(String agency_id, String city, String postalAddress,
                     String surfaceArea, String constructionYear, String numOfBed, String price, String status,
                     Bitmap photo, String availability_date, String description, String garden, String balcony) {
        SQLiteDatabase db = this.getWritableDatabase();


           byteArrayOutputStream = new ByteArrayOutputStream() ;
           photo.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream) ;
           imageInbytes = byteArrayOutputStream.toByteArray() ;
           ContentValues cv = new ContentValues();
           cv.put("id", i);
           cv.put("agency_id", agency_id);
           cv.put("city", city);
           cv.put("postalAddress", postalAddress);
           cv.put("surfaceArea", surfaceArea);
           cv.put("constructionYear", constructionYear);
           cv.put("numOfBed", numOfBed);
           cv.put("price", price);
           cv.put("status", status);
           cv.put("photo", imageInbytes);
           cv.put("availability_date", availability_date);
           cv.put("description",description);
           cv.put("garden",garden);
           cv.put("balcony",balcony);
           long res = db.insert("property",null,cv)  ;
           i++ ;
           if(res != -1){
               Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
           }else{
               Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
           }


    }

    void addAgency(String email, String agency_name, String password, String confirm,  String phone, String country, String city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("name", agency_name);
        cv.put("password", password);
        cv.put("confirm", confirm);
        cv.put("phone", phone);
        cv.put("country",country);
        cv.put("city",city);
        long res = db.insert("agency",null,cv)  ;
        if(res==-1){
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor getAllTenant(){
        String query = "SELECT email, password FROM tenant" ;
        SQLiteDatabase db = this.getReadableDatabase() ;
        Cursor cursor=null ;
        if(db!=null){
           cursor =  db.rawQuery(query,null) ;
        }
        return cursor ;
    }
    Cursor getAllAgency(){
        String query = "SELECT email, password FROM agency" ;
        SQLiteDatabase db = this.getReadableDatabase() ;
        Cursor cursor=null ;
        if(db!=null){
            cursor=db.rawQuery(query,null) ;
        }
        return cursor ;
    }
    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from tenant where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * from tenant where email  = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkusernamepasswordAgency(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * from agency where email  = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
