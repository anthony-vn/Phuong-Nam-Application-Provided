package com.anthonycode.bookstorephuongnamaplication.Model;

public class TheLoai {
    private int id;
    private String maTheLoai;
    private String tenTheLoai;
    private String moTa;

    public TheLoai() {
    }

    public TheLoai(int id, String maTheLoai, String tenTheLoai, String moTa) {
        this.id = id;
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.moTa = moTa;
    }

    public TheLoai(String maTheLoai, String tenTheLoai, String moTa) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.moTa = moTa;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Override
//    public String toString() {
//        return getMaTheLoai()+" | "+getTenTheLoai();
//    }

    @Override
    public String toString() {
        return "TheLoai{" +
                "id='" + id + '\'' +
                ", matheloai='" + maTheLoai + '\'' +
                ", tentheloai='" + tenTheLoai + '\'' +
                ", mota=" + moTa +
                '}';
    }
}
