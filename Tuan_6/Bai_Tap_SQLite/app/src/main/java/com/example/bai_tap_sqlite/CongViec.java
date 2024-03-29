package com.example.bai_tap_sqlite;

import java.io.Serializable;

public class CongViec implements Serializable {
    private int idCV;
    private String tenCV;
    public CongViec(){}

    public CongViec(int idCV, String tenCV) {
        this.idCV = idCV;
        this.tenCV = tenCV;
    }

    public int getIdCV() {
        return idCV;
    }

    public void setIdCV(int idCV) {
        this.idCV = idCV;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    @Override
    public String toString() {
        return "CongViec{" +
                "idCV=" + idCV +
                ", tenCV='" + tenCV + '\'' +
                '}';
    }
}
