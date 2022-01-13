package com.example.and.Model;

public class Tenant {
    private String email ;

    private String first_name ;
    private String last_name ;
    private String gender ;
    private String Password ;
    private String confirm ;
    private String nationality ;
    private String monthly_salary ;
    private String occupation ;
    private String family_size ;
    private String current_country ;
    private String city ;
    private String phone ;
    public Tenant( String monthly_salary,String family_size,  String phone) {
        this.monthly_salary = monthly_salary;
        this.family_size = family_size;
        this.phone = phone;
    }

    public Tenant(String email, String first_name, String last_name, String gender, String password, String confirm, String nationality, String monthly_salary, String occupation, String family_size, String current_country, String city, String phone) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        Password = password;
        this.confirm = confirm;
        this.nationality = nationality;
        this.monthly_salary = monthly_salary;
        this.occupation = occupation;
        this.family_size = family_size;
        this.current_country = current_country;
        this.city = city;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMonthly_salary() {
        return monthly_salary;
    }

    public void setMonthly_salary(String monthly_salary) {
        this.monthly_salary = monthly_salary;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getFamily_size() {
        return family_size;
    }

    public void setFamily_size(String family_size) {
        this.family_size = family_size;
    }

    public String getCurrent_country() {
        return current_country;
    }

    public void setCurrent_country(String current_country) {
        this.current_country = current_country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
