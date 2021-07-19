package com.example.hclflim.Object;

import java.util.ArrayList;
import java.util.List;

public class DanhMuc {
  private ArrayList<Phim> phims;
  private String MaDM,TenDM;
    public void setData(String maDM,String tenDM, ArrayList<Phim> phims)
    {
        this.MaDM = maDM ;
        this.TenDM = tenDM;
        this.phims = phims;
    }
    public String getMaDM() {
        return MaDM;
    }

    public void setMaDM(String maDM) {
        MaDM = maDM;
    }

    public String getTenDM() {
        return TenDM;
    }

    public void setTenDM(String tenDM) {
        TenDM = tenDM;
    }

    public DanhMuc(ArrayList<Phim> phims) {
        this.phims = phims;
    }

    public DanhMuc() {
    }

    public ArrayList<Phim> getPhims() {
        return phims;
    }

    public void setPhims(ArrayList<Phim> phims) {
        this.phims = phims;
    }
}
