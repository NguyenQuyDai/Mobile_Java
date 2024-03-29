package com.example.myapplication.models;

import java.io.Serializable;

public class ThietBi implements Serializable {
    private int ID;
    private String maTB,tenTB;
    private double giaTB;
    private  byte[] hinhAnhTB;
    public ThietBi(){}

    public ThietBi(int ID, String maTB, String tenTB, double giaTB, byte[] hinhAnhTB) {
        this.ID = ID;
        this.maTB = maTB;
        this.tenTB = tenTB;
        this.giaTB = giaTB;
        this.hinhAnhTB = hinhAnhTB;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaTB() {
        return maTB;
    }

    public void setMaTB(String maTB) {
        this.maTB = maTB;
    }

    public String getTenTB() {
        return tenTB;
    }

    public void setTenTB(String tenTB) {
        this.tenTB = tenTB;
    }

    public double getGiaTB() {
        return giaTB;
    }

    public void setGiaTB(double giaTB) {
        this.giaTB = giaTB;
    }

    public byte[] getHinhAnhTB() {
        return hinhAnhTB;
    }

    public void setHinhAnhTB(byte[] hinhAnhTB) {
        this.hinhAnhTB = hinhAnhTB;
    }
}
