package com.example.fastfooddelivery.Model;

public class IconUser {
    private int Image;
    private String Name;

    public IconUser(int image, String name) {
        Image = image;
        Name = name;
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
}
