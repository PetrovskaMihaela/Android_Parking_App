package com.example.easypark;

public class Reservation {

    String user;
    String city;
    String park;
    String date;
    String time;

    public Reservation() {
    }

    public String getUserR() {
        return user;
    }

    public void setUserR(String user) {
        this.user = user;
    }

    public String getCityR() {
        return city;
    }

    public void setCityR(String city) {
        this.city = city;
    }

    public String getParkR() {
        return park;
    }

    public void setParkR(String park) {
        this.park = park;
    }

    public String getDateR() {
        return date;
    }

    public void setDateR(String date) {
        this.date = date;
    }

    public String getTimeR() {
        return time;
    }

    public void setTimeR(String time) {
        this.time = time;
    }
}

