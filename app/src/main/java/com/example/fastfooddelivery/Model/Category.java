package com.example.fastfooddelivery.Model;

public class Category {
    private int image_cate;
    private String title_cate;


    public Category(){

    }

    public Category(int image_cate, String title_cate) {
        this.image_cate = image_cate;
        this.title_cate = title_cate;
    }

    public int getImage_cate() {
        return image_cate;
    }

    public void setImage_cate(int image_cate) {
        this.image_cate = image_cate;
    }

    public String getTitle_cate() {
        return title_cate;
    }

    public void setTitle_cate(String title_cate) {
        this.title_cate = title_cate;
    }
}
