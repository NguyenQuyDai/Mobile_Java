package com.example.rss;

import java.io.Serializable;

public class BaiBao implements Serializable {
    String tenBaiBao;
    String linkBaiBao;
    String linkAnhBaiBao;
    String tomTatBaiBao;
    String ngayDangBai;

    public  BaiBao(){}

    public BaiBao(String tenBaiBao, String linkBaiBao, String linkAnhBaiBao, String tomTatBaiBao, String ngayDangBai) {
        this.tenBaiBao = tenBaiBao;
        this.linkBaiBao = linkBaiBao;
        this.linkAnhBaiBao = linkAnhBaiBao;
        this.tomTatBaiBao = tomTatBaiBao;
        this.ngayDangBai = ngayDangBai;
    }

    public String getTenBaiBao() {
        return tenBaiBao;
    }

    public void setTenBaiBao(String tenBaiBao) {
        this.tenBaiBao = tenBaiBao;
    }

    public String getLinkBaiBao() {
        return linkBaiBao;
    }

    public void setLinkBaiBao(String linkBaiBao) {
        this.linkBaiBao = linkBaiBao;
    }

    public String getLinkAnhBaiBao() {
        return linkAnhBaiBao;
    }

    public void setLinkAnhBaiBao(String linkAnhBaiBao) {
        this.linkAnhBaiBao = linkAnhBaiBao;
    }

    public String getTomTatBaiBao() {
        return tomTatBaiBao;
    }

    public void setTomTatBaiBao(String tomTatBaiBao) {
        this.tomTatBaiBao = tomTatBaiBao;
    }

    public String getNgayDangBai() {
        return ngayDangBai;
    }

    public void setNgayDangBai(String ngayDangBai) {
        this.ngayDangBai = ngayDangBai;
    }

    @Override
    public String toString() {
        return "BaiBao{" +
                "tenBaiBao='" + tenBaiBao + '\'' +
                ", linkBaiBao='" + linkBaiBao + '\'' +
                ", linkAnhBaiBao='" + linkAnhBaiBao + '\'' +
                ", tomTatBaiBao='" + tomTatBaiBao + '\'' +
                ", ngayDangBai='" + ngayDangBai + '\'' +
                '}';
    }
}
