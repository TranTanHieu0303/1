package com.example.hclflim.Object;

public class QuocGia {
    String MaQG,TenQG;

    public QuocGia(String maQG, String tenQG) {
        MaQG = maQG;
        TenQG = tenQG;
    }

    public QuocGia() {
    }

    public String getMaQG() {
        return MaQG;
    }

    public void setMaQG(String maQG) {
        MaQG = maQG;
    }

    public String getTenQG() {
        return TenQG;
    }

    public void setTenQG(String tenQG) {
        TenQG = tenQG;
    }
}
