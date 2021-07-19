package com.example.hclflim.Object;

import java.io.Serializable;

public class Phim implements Serializable {
    private String MaPhim ,TenPhim ,DanhMuc,TheLoai,QuocGia , MoTa , HinhAnh ,Dienvien,Thoiluong ,sotap,LinkTrailer,LinkPhim ;

    public Phim(String maPhim, String tenPhim, String DanhMuc, String theLoai, String quocGia, String moTa, String hinhAnh, String dienvien, String thoiluong, String sotap, String linkTrailer, String linkPhim) {
        MaPhim = maPhim;
        TenPhim = tenPhim;
        this.DanhMuc = DanhMuc;
        TheLoai = theLoai;
        QuocGia = quocGia;
        MoTa = moTa;
        HinhAnh = hinhAnh;
        Dienvien = dienvien;
        Thoiluong = thoiluong;
        this.sotap = sotap;
        LinkTrailer = linkTrailer;
        LinkPhim = linkPhim;
    }

    public Phim() {
    }

    public String getMaPhim() {
        return MaPhim;
    }

    public void setMaPhim(String maPhim) {
        MaPhim = maPhim;
    }

    public String getTenPhim() {
        return TenPhim;
    }

    public void setTenPhim(String tenPhim) {
        TenPhim = tenPhim;
    }

    public String getdanhmuc() {
        return DanhMuc;
    }

    public void setdanhmuc(String DanhMuc) {
        this.DanhMuc = DanhMuc;
    }

    public String getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(String theLoai) {
        TheLoai = theLoai;
    }

    public String getQuocGia() {
        return QuocGia;
    }

    public void setQuocGia(String quocGia) {
        QuocGia = quocGia;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getDienvien() {
        return Dienvien;
    }

    public void setDienvien(String dienvien) {
        Dienvien = dienvien;
    }

    public String getThoiluong() {
        return Thoiluong;
    }

    public void setThoiluong(String thoiluong) {
        Thoiluong = thoiluong;
    }

    public String getSotap() {
        return sotap;
    }

    public void setSotap(String sotap) {
        this.sotap = sotap;
    }

    public String getLinkTrailer() {
        return LinkTrailer;
    }

    public void setLinkTrailer(String linkTrailer) {
        LinkTrailer = linkTrailer;
    }

    public String getLinkPhim() {
        return LinkPhim;
    }

    public void setLinkPhim(String linkPhim) {
        LinkPhim = linkPhim;
    }
}
