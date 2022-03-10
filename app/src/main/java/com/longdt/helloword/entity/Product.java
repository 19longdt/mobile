package com.longdt.helloword.entity;

import com.longdt.helloword.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
    private int id;
    private String name;
    private int amount;
    private double price;
    private int cateId;
    private int img;
    private List<Product> list;

    public List<Product> getList() {
        list = new ArrayList<>();
        list.add( new Product(1, "Iphone10", 5, 10.0, 1, R.drawable.ip10));
        list.add( new Product(2, "Iphone10s", 5, 11.0, 1, R.drawable.ipxpro));
        list.add( new Product(3, "Iphone10sMax", 5, 12.0, 1, R.drawable.ipxpromax));

        list.add( new Product(4, "Iphone11", 5, 11.0, 2, R.drawable.ip11));
        list.add( new Product(5, "Iphone11 pro", 5, 12.0, 2, R.drawable.ip11pro));
        list.add( new Product(6, "Iphone11 proMax", 5, 13.0, 2, R.drawable.ip11promax));
        return list;
    }

    public Product() {
    }

    public Product(int id, String name, int amount, double price, int cateId) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.cateId = cateId;
    }

    public Product(int id, String name, int amount, double price, int cateId, int img) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.cateId = cateId;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }
}
