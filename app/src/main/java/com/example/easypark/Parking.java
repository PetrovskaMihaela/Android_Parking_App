package com.example.easypark;

public class Parking {
    String parkName;
    String parkCity;
    int parkSpaces;
    int takenSpaces;

    public Parking() {
    }

    public Parking(String name, String city, int br, int zaf){
        this.parkName = name;
        this.parkCity = city;
        this.parkSpaces = br;
        this.takenSpaces = zaf;

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
