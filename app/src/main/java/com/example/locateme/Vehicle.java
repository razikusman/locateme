package com.example.locateme;

import android.widget.EditText;

public class Vehicle {

    private String number;
    private String vehicle;
    private String from;
    private String to;


    public Vehicle() {

    }

    public Vehicle(String number, String vehicle, String from, String to) {
        this.number = number;
        this.vehicle = vehicle;
        this.from = from;
        this.to = to;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String toString(){
        return"Vehicle Number==>"+ this. number + "\nVehicle Type==>" + vehicle + "\n route==> \tFrom -- " + from + "\n\t\t\t\t\t\tTo -- " + to;
    }

}