package com.webonise.model;


public class Location {

    private static Location instance;

    private float latitude;
    private float longitude;
    private float height;

    private Location() {
    }

    public static Location getInstance() {
        if (instance == null) {
            instance = new Location();
        }
        return instance;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
