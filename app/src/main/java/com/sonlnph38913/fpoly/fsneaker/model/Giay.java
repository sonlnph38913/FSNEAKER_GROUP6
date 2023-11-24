package com.sonlnph38913.fpoly.fsneaker.model;

public class Giay {
private int magiay;
private String tengiay;
private String hinhanh;
private int size;
private int giaban;
private int soluong;

    public Giay(int id, String name, String imgName, int size, int gia, int maloai, int soluong) {
    }

    public Giay(int magiay, String tengiay, String hinhanh, int size, int giaban, int soluong) {
        this.magiay = magiay;
        this.tengiay = tengiay;
        this.hinhanh = hinhanh;
        this.size = size;
        this.giaban = giaban;
        this.soluong = soluong;
    }

    public int getMagiay() {
        return magiay;
    }

    public void setMagiay(int magiay) {
        this.magiay = magiay;
    }

    public String getTengiay() {
        return tengiay;
    }

    public void setTengiay(String tengiay) {
        this.tengiay = tengiay;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
