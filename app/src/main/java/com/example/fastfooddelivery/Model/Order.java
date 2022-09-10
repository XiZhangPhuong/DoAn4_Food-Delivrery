package com.example.fastfooddelivery.Model;

public class Order {
    private String Id;
    private String Name;
    private double Price;
    private String Address;
    private int Image;
    private String Information;
    private boolean Check;

    public Order(String id, String name, double price, String address, int image, String information, boolean check) {
        Id = id;
        Name = name;
        Price = price;
        Address = address;
        Image = image;
        Information = information;
        Check = check;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }

    public boolean isCheck() {
        return Check;
    }

    public void setCheck(boolean check) {
        Check = check;
    }
}
