package com.example.equipme;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Equipment extends Displayable implements Parcelable {
    private String brand;
    private String type;
    private String serialNumber;
    private String assignedTo;
    private Date dateGiven;
    public String notes;

    /**************************************************************************
     * DEFAULT CONSTRUCTOR
     **************************************************************************/
    public Equipment() {
        this.assignedTo = "No one assigned";
        this.dateGiven = new Date();
    }

    /**************************************************************************
     * NON-DEFAULT CONSTRUCTOR
     **************************************************************************/
    public Equipment(String brand, String type, String serialNumber, String assignedTo, Date dateGiven, String notes) {
        this.brand = brand;
        this.type = type;
        this.serialNumber = serialNumber;
        this.assignedTo = assignedTo;
        this.dateGiven = dateGiven;
        this.notes = notes;
        setMyKey();
    }


    protected Equipment(Parcel in) {
        brand = in.readString();
        type = in.readString();
        serialNumber = in.readString();
        assignedTo = in.readString();
        notes = in.readString();
    }

    public static final Creator<Equipment> CREATOR = new Creator<Equipment>() {
        @Override
        public Equipment createFromParcel(Parcel in) {
            return new Equipment(in);
        }

        @Override
        public Equipment[] newArray(int size) {
            return new Equipment[size];
        }
    };

    // Getters
    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public Date getDateGiven() {
        return dateGiven;
    }

    public String getNotes() {
        return notes;
    }

    // Setters
    public void setBrand(String brand) {
        this.brand = brand;
        setMyKey();
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        setMyKey();
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setDateGiven(Date dateGiven) {
        this.dateGiven = dateGiven;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void display() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(brand);
        parcel.writeString(type);
        parcel.writeString(serialNumber);
        parcel.writeString(assignedTo);
        parcel.writeString(notes);
    }

    /**************************************************************************
     * GET DISPLAY STRING
     *
     * Returns the single string to represent the object in a list
     **************************************************************************/
    @Override
    public String getDisplayString() {
        return brand + " " + type;
    }

    /**************************************************************************
     * SET MY KEY
     *
     * Sets key according to internal values. For Equipment, it's based on brand
     * and serial number
     **************************************************************************/
    @Override
    public void setMyKey() {
        if (brand != null && serialNumber != null) {
            myKey = brand + serialNumber;
        }
    }
}
