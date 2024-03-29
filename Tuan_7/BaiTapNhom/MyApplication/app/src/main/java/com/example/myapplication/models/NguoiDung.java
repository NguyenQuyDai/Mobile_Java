package com.example.myapplication.models;

import java.io.Serializable;

public class NguoiDung implements Serializable {
    private int user_id;
    private  String tenDangNhap, matKhau;

    public NguoiDung(){}
    public NguoiDung(int user_id, String tenDangNhap, String matKhau) {
        this.user_id = user_id;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
