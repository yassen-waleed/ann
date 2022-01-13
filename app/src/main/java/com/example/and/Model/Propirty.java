package com.example.and.Model;

import android.graphics.Bitmap;

public class Propirty {

   private String id;
   private String agency_email;
    private  String city;
    private  String postal_address;
    private   String surface_area;
    private  String construction_year;
    private   String numOfBed;
    private  String rental_price;
    private  String status;
    private  byte[] photo;
    private  String availability_date;
    private   String description;
    private String garden ;
    private String balcony ;
    private String rented ;
    public Propirty(String id, String agency_email, String city, String postal_address, String surface_area, String construction_year, String numOfBed, String rental_price, String status, String availability_date, String description, String garden, String balcony) {
        this.id = id;
        this.agency_email = agency_email;
        this.city = city;
        this.postal_address = postal_address;
        this.surface_area = surface_area;
        this.construction_year = construction_year;
        this.numOfBed = numOfBed;
        this.rental_price = rental_price;
        this.status = status;
        this.availability_date = availability_date;
        this.description = description;
        this.garden = garden;
        this.balcony = balcony;

    }

    public Propirty(String id, String agency_email, String city, String postal_address, String surface_area, String construction_year, String numOfBed, String rental_price, String status, String availability_date, String description, String garden, String balcony,String rented) {
        this.id = id;
        this.agency_email = agency_email;
        this.city = city;
        this.postal_address = postal_address;
        this.surface_area = surface_area;
        this.construction_year = construction_year;
        this.numOfBed = numOfBed;
        this.rental_price = rental_price;
        this.status = status;
        this.availability_date = availability_date;
        this.description = description;
        this.garden = garden;
        this.balcony = balcony;
        this.rented = rented;
    }

    public String getRented() {
        return rented;
    }

    public void setRented(String rented) {
        this.rented = rented;
    }

    public Propirty(String agency_email, String city, String postal_address, String surface_area, String construction_year, String numOfBed, String rental_price, String status, String availability_date, String description, String garden, String balcony) {
        this.agency_email = agency_email;
        this.city = city;
        this.postal_address = postal_address;
        this.surface_area = surface_area;
        this.construction_year = construction_year;
        this.numOfBed = numOfBed;
        this.rental_price = rental_price;
        this.status = status;
        this.availability_date = availability_date;
        this.description = description;
        this.garden = garden;
        this.balcony = balcony;
    }

    public Propirty(String agency_email, String city, String postal_address, String surface_area, String construction_year, String numOfBed, String rental_price, String status, byte[] photo, String availability_date, String description) {
        this.agency_email = agency_email;
        this.city = city;
        this.postal_address = postal_address;
        this.surface_area = surface_area;
        this.construction_year = construction_year;
        this.numOfBed = numOfBed;
        this.rental_price = rental_price;
        this.status = status;
        this.photo = photo;
        this.availability_date = availability_date;
        this.description = description;
    }
    public Propirty( String city, String surface_area,  String rental_price, String status) {

        this.city = city;
        this.surface_area = surface_area;
        this.rental_price = rental_price;
        this.status = status;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgency_email() {
        return agency_email;
    }

    public void setAgency_email(String agency_email) {
        this.agency_email = agency_email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_address() {
        return postal_address;
    }

    public void setPostal_address(String postal_address) {
        this.postal_address = postal_address;
    }

    public String getSurface_area() {
        return surface_area;
    }

    public void setSurface_area(String surface_area) {
        this.surface_area = surface_area;
    }

    public String getConstruction_year() {
        return construction_year;
    }

    public void setConstruction_year(String construction_year) {
        this.construction_year = construction_year;
    }

    public String getNumOfBed() {
        return numOfBed;
    }

    public void setNumOfBed(String numOfBed) {
        this.numOfBed = numOfBed;
    }

    public String getRental_price() {
        return rental_price;
    }

    public void setRental_price(String rental_price) {
        this.rental_price = rental_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getAvailability_date() {
        return availability_date;
    }

    public void setAvailability_date(String availability_date) {
        this.availability_date = availability_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGarden() {
        return garden;
    }

    public void setGarden(String garden) {
        this.garden = garden;
    }

    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }
}
