package com.example.carlist;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Car {
    private String model;

    public Car(String model, int passengers, String color, Bitmap bitmap, int speed) {
        this.model = model;
        this.passengers = passengers;
        this.color = color;
        this.bitmap = bitmap;
        this.speed = speed;
    }

    private int passengers;
    private String color;
    private Bitmap bitmap;
    private int speed;

//    public Car(String mod, int pas, String col, byte[] bitmapToArray, int spe) {
//        this.model = model;
//        this.passengers = passengers;
//        this.color = color;
//        this.bitmap = bitmap;
//        this.speed = speed;
//    }


    public String getModel() {
        return model;
    }

    public int getPassengers() {
        return passengers;
    }

    public String getColor() {
        return color;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getSpeed() {
        return speed;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
