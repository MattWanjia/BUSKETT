package com.example.matt.buskett;

/**
 * Created by matt on 5/11/2018.
 */

public class User {
    private String Fname, Sname, Location;
    private int ProfPic;

    public User() {
    }

    public User(String fname, String sname, String location, int profPic) {
        Fname = fname;
        Sname = sname;
        Location = location;
        ProfPic = profPic;
    }

    public String getFname() {
        return Fname;
    }

    public String getSname() {
        return Sname;
    }

    public String getLocation() {
        return Location;
    }

    public int getProfPic() {
        return ProfPic;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setProfPic(int profPic) {
        ProfPic = profPic;
    }
}
