package com.example.myapplication;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int id;
    private String tenSP,giaSP;
    private  byte[] hinhAnh;
    public SanPham(){}

    public SanPham(int id, String tenSP, String giaSP, byte[] hinhAnh) {
        this.id = id;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.hinhAnh = hinhAnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(String giaSP) {
        this.giaSP = giaSP;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

//    CREATE TABLE ThietBi (
//            ID             INTEGER        PRIMARY KEY AUTOINCREMENT,
//            MaThietBi      NVARCHAR (255),
//    TenThietBi     NVARCHAR (255),
//    GiaThietBi     FLOAT,
//    HinhAnhThietBi BLOB
//);

}
