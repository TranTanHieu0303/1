package com.example.hclflim.Object;

import java.util.ArrayList;
import java.io.Serializable;

public class TaiKhoan implements Serializable {
    String TenKT,SDT,MatKhau;
    ArrayList<Phim> DaXem,YeuThich;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenKT, String SDT, String matKhau) {
        TenKT = tenKT;
        this.SDT = SDT;
        MatKhau = matKhau;
    }

    public String getTenKT() {
        return TenKT;
    }

    public void setTenKT(String tenKT) {
        TenKT = tenKT;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public ArrayList<Phim> getDaXem() {
        return DaXem;
    }

    public void setDaXem(ArrayList<Phim> daXem) {
        DaXem = daXem;
    }

    public ArrayList<Phim> getYeuThich() {
        return YeuThich;
    }

    public void setYeuThich(ArrayList<Phim> yeuThich) {
        YeuThich = yeuThich;
    }
}
