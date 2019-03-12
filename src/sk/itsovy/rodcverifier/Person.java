package sk.itsovy.rodcverifier;

import java.util.Date;

public class Person {
    private String fname;
    private String lname;
    private Date dob;
    private String pin;

    public Person(String fname, String lname, Date dob, String pin) {
        this.fname = fname;
        this.lname = lname;
        this.pin = pin;
        this.dob = dob;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
