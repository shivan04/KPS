package sr.unasat.kpsfinetracker.entities;

import java.sql.Date;

public class Person {
    private long id;
    private String lastname, firstname, id_number, email_address, phone_number, address;
    private Date dob;

    public Person(long id, String lastname, String firstname, String id_number, Date dob, String email_address, String phone_number, String address) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.id_number = id_number;
        this.dob = dob;
        this.email_address = email_address;
        this.phone_number = phone_number;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
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
