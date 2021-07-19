package com.example.hclflim.Object;

public class TheLoai {
    String MaTL,TenTL;

    public TheLoai(String maTL, String tenTL) {
        MaTL = maTL;
        TenTL = tenTL;
    }

    public TheLoai() {
    }

    public String getMaTL() {
        return MaTL;
    }

    public void setMaTL(String maTL) {
        MaTL = maTL;
    }

    public String getTenTL() {
        return TenTL;
    }

    public void setTenTL(String tenTL) {
        TenTL = tenTL;
    }
}
