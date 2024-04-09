package com.example.baitap1;

public class Product {
    private int id;
    private String name;
    private String mota;
    private float price;
    private int hinh;
    private int add;

    public Product(String name, String mota, float price, int hinh, int add) {
        this.name = name;
        this.mota = mota;
        this.price = price;
        this.hinh = hinh;
        this.add = add;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public int getAdd() {
        return add;
    }

    public void setAdd(int add) {
        this.add = add;
    }
}
