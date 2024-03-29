package com.example.luyen_tap;

import java.io.Serializable;

public class MonHoc implements Serializable {
    private int id;
    private String maMH,tenMH;
    private byte[] hinhAnh;

    public MonHoc(int id, String maMH, String tenMH, byte[] hinhAnh) {
        this.id = id;
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.hinhAnh = hinhAnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
