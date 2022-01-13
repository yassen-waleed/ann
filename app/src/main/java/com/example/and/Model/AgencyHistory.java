package com.example.and.Model;
public class AgencyHistory  {
    private String Tenant_email;
    private String first_name;
    private String last_name;
    private String house_id;
    private String postal;
    private String city;
    private String status;
    private String start ;
    private String end ;

    public AgencyHistory(String tenant_email, String first_name, String last_name, String house_id, String postal, String city, String status, String start, String end) {
        Tenant_email = tenant_email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.house_id = house_id;
        this.postal = postal;
        this.city = city;
        this.status = status;
        this.start = start;
        this.end = end;
    }

    public AgencyHistory(String tenant_email, String first_name, String last_name, String house_id, String postal, String city, String status) {
        Tenant_email = tenant_email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.house_id = house_id;
        this.postal = postal;
        this.city = city;
        this.status = status;
    }

    public String getTenant_email() {
        return Tenant_email;
    }

    public void setTenant_email(String tenant_email) {
        Tenant_email = tenant_email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getHouse_id() {
        return house_id;
    }

    public void setHouse_id(String house_id) {
        this.house_id = house_id;
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

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
