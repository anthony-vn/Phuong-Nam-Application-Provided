package com.anthonycode.bookstorephuongnamaplication.Model;

public class User {
    private int id;
    private String userName;
    private String password;
    private String phone;
    private String hoTen;

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(int id, String userName, String password, String phone, String hoTen) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.hoTen = hoTen;
    }

    public User(String userName, String password, String phone, String hoTen) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.hoTen = hoTen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

//    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", hoTen='" + hoTen + '\'' +
                '}';
    }
}
