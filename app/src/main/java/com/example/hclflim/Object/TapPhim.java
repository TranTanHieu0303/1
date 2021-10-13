package com.example.hclflim.Object;

import java.io.Serializable;

public class TapPhim implements Serializable {
    String HinhAnh,LinkTap,MaTap,TenTap;

    public TapPhim(String hinhAnh, String linkTap, String maTap, String tenTap) {
        HinhAnh = hinhAnh;
        LinkTap = linkTap;
        MaTap = maTap;
        TenTap = tenTap;
    }

    public TapPhim() {
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getLinkTap() {
        return LinkTap;
    }

    public void setLinkTap(String linkTap) {
        LinkTap = linkTap;
    }

    public String getMaTap() {
        return MaTap;
    }

    public void setMaTap(String maTap) {
        MaTap = maTap;
    }

    public String getTenTap() {
        return TenTap;
    }

    public void setTenTap(String tenTap) {
        TenTap = tenTap;
    }
}
