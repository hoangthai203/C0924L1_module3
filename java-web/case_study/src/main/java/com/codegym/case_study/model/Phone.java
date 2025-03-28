package com.codegym.case_study.model;

public class Phone {
    private int id;
    private String ten;
    private String thuongHieu;
    private String model;
    private int namSanXuat;
    private double gia;
    private int ram;
    private int boNhoTrong;
    private int dungLuongPin;
    private String heDieuHanh;
    private String trangThai;
    private String hinhAnh;  // 🛠 Thêm thuộc tính hình ảnh

    public Phone(int id, String ten, String thuongHieu, String model, int namSanXuat, double gia, int ram, int boNhoTrong, int dungLuongPin, String heDieuHanh, String trangThai, String hinhAnh) {
        this.id = id;
        this.ten = ten;
        this.thuongHieu = thuongHieu;
        this.model = model;
        this.namSanXuat = namSanXuat;
        this.gia = gia;
        this.ram = ram;
        this.boNhoTrong = boNhoTrong;
        this.dungLuongPin = dungLuongPin;
        this.heDieuHanh = heDieuHanh;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;  // 🛠 Khởi tạo giá trị hình ảnh
    }

    public int getId() { return id; }
    public String getTen() { return ten; }
    public String getThuongHieu() { return thuongHieu; }
    public String getModel() { return model; }
    public int getNamSanXuat() { return namSanXuat; }
    public double getGia() { return gia; }
    public int getRam() { return ram; }
    public int getBoNhoTrong() { return boNhoTrong; }
    public int getDungLuongPin() { return dungLuongPin; }
    public String getHeDieuHanh() { return heDieuHanh; }
    public String getTrangThai() { return trangThai; }

    public String getHinhAnh() { return hinhAnh; }  // 🛠 Getter cho hình ảnh
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }  // 🛠 Setter cho hình ảnh
}
