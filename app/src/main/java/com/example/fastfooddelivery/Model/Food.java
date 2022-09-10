package com.example.fastfooddelivery.Model;

import java.io.Serializable;

public class Food implements Serializable {
    private String ID;
    private int Image;
    private String Name;
    private double Price;
    private boolean Checked;
    private int Count;

    public Food(String ID, int image, String name, double price, boolean checked, int count) {
        this.ID = ID;
        Image = image;
        Name = name;
        Price = price;
        Checked = checked;
        Count = count;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public boolean isChecked() {
        return Checked;
    }

    public void setChecked(boolean checked) {
        Checked = checked;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
