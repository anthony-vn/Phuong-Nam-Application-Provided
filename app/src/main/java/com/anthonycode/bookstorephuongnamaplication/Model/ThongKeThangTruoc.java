package com.anthonycode.bookstorephuongnamaplication.Model;

public class ThongKeThangTruoc {
    private int image;
    private String date;
    private String money;

    public ThongKeThangTruoc(int image, String date, String money) {
        this.image = image;
        this.date = date;
        this.money = money;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
