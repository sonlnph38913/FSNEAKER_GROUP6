package com.sonlnph38913.fpoly.fsneaker.model;

public class ThanhVien {
    private int matv;
    private String hoten;
    private String namsinh;

    private String imgTv;

    public ThanhVien() {
    }

    public ThanhVien(int matv, String hoten, String namsinh, String imgTv) {
        this.matv = matv;
        this.hoten = hoten;
        this.namsinh = namsinh;
        this.imgTv = imgTv;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public String getImgTv() {
        return imgTv;
    }

    public void setImgTv(String imgTv) {
        this.imgTv = imgTv;
    }
}
