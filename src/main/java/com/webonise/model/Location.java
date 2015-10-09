package com.webonise.model;


public class Location {

    private static Location instance;

    private float lattitude;
    private float longitude;
    private float height;

    private Location(){
    }

    public static Location getInstance(){
        if(instance==null){
            instance = new Location();
        }
        return instance;
    }

    public float getLattitude() {
        return lattitude;
    }

    public void setLattitude(float lattitude) {
        this.lattitude = lattitude;
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
