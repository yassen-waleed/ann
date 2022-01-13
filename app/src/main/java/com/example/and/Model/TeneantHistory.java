package com.example.and.Model;

public class TeneantHistory {
    private String agency_email;
    private String rental_price;
    private String postal;
    private String city;
    private String status;
    private String start ;
    private String end ;

    public TeneantHistory(String agency_email, String rental_price, String postal, String city, String status, String start, String end) {
        this.agency_email = agency_email;
        this.rental_price = rental_price;
        this.postal = postal;
        this.city = city;
        this.status = status;
        this.start = start;
        this.end = end;
    }

    public TeneantHistory(String agency_email, String rental_price, String postal, String city, String status) {
        this.agency_email = agency_email;
        this.rental_price = rental_price;
        this.postal = postal;
        this.city = city;
        this.status = status;
    }


    public String getAgency_email() {
        return agency_email;
    }

    public void setAgency_email(String agency_email) {
        this.agency_email = agency_email;
    }

    public String getRental_price() {
        return rental_price;
    }

    public void setRental_price(String rental_price) {
        this.rental_price = rental_price;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCity() {
        return city;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
