package sr.unasat.kpsfinetracker.entities;

import java.sql.Date;

public class Person {
    private int id;
    private String lastname, firstname, id_number, email_address, phone_number, address;
    private Date dob;

    public Person(int id, String lastname, String firstname, String id_number, String email_address, String phone_number, String address) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.id_number = id_number;
        this.email_address = email_address;
        this.phone_number = phone_number;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getIdNumber() {
        return id_number;
    }

    public void setIdNumber(String id_number) {
        this.id_number = id_number;
    }

    public String getEmailAddress() {
        return email_address;
    }

    public void setEmailAddress(String email_address) {
        this.email_address = email_address;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
