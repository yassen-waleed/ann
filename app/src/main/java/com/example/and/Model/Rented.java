package com.example.and.Model;

public class Rented {
    private String id;
    private String postal;
    private String email;
    private String status;
    private String firstname;
    private String lastname;
    private String agency_email;
    private String salary;
    private String family;
    private String phone;

    public Rented(String id, String postal, String email, String status, String firstname, String lastname, String agency_email, String salary, String family, String phone) {
        this.id = id;
        this.postal = postal;
        this.email = email;
        this.status = status;
        this.firstname = firstname;
        this.lastname = lastname;
        this.agency_email = agency_email;
        this.salary = salary;
        this.family = family;
        this.phone = phone;
    }

    public Rented(String id, String postal, String email, String status, String firstname, String lastname, String agency_email) {
        this.id = id;
        this.postal = postal;
        this.email = email;
        this.status = status;
        this.firstname = firstname;
        this.lastname = lastname;
        this.agency_email = agency_email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getAgency_email() {
        return agency_email;
    }

    public void setAgency_email(String agency_email) {
        this.agency_email = agency_email;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
