package com.example.easypark;

public class Parking {
    String parkName;
    String parkCity;
    int parkSpaces;
    int takenSpaces;
    double lat;
    double lng;

    public Parking() {
    }

    public Parking(String name, String city, int br, int zaf, double lat, double lng){
        this.parkName = name;
        this.parkCity = city;
        this.parkSpaces = br;
        this.takenSpaces = zaf;
        this.lat = lat;
        this.lng = lng;

    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getParkCity() {
        return parkCity;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setParkCity(String parkCity) {
        this.parkCity = parkCity;
    }

    public int getParkSpaces() {
        return parkSpaces;
    }

    public void setParkSpaces(int parkSpaces) {
        this.parkSpaces = parkSpaces;
    }

    public int getTakenSpaces() {
        return takenSpaces;
    }

    public void setTakenSpaces(int takenSpaces) {
        this.takenSpaces = takenSpaces;
    }
}
