package com.codegym.case_study.model;

public class DichVu {
    private int idDichVu; // Trùng với id_dich_vu trong DB
    private String ten;
    private String moTa;
    private double gia;
    private String trangThai;

    // Constructor
    public DichVu(int idDichVu, String ten, String moTa, double gia, String trangThai) {
        this.idDichVu = idDichVu;
        this.ten = ten;
        this.moTa = moTa;
        this.gia = gia;
        this.trangThai = trangThai;
    }

    // Getter & Setter
    public int getIdDichVu() {
        return idDichVu;
    }

    public void setIdDichVu(int idDichVu) {
        this.idDichVu = idDichVu;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
