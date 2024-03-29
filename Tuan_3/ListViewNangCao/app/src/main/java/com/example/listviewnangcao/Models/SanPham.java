package com.example.listviewnangcao.Models;

import java.io.Serializable;

public class SanPham implements Serializable {
    private  String maSP,tenSP;
    private  double giaSP;
    private  int hinhAnhSP;
    public SanPham() {}

    public SanPham(String maSP, String tenSP, double giaSP, int hinhAnhSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.hinhAnhSP = hinhAnhSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(double giaSP) {
        this.giaSP = giaSP;
    }

    public int getHinhAnhSP() {
        return hinhAnhSP;
    }

    public void setHinhAnhSP(int hinhAnhSP) {
        this.hinhAnhSP = hinhAnhSP;
    }
}
