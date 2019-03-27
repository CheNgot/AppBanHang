package com.example.quocthai.appbanhang.model;

public class Sanpham
{
    private int Id;
    private String TenSanPham;
    private Integer GiaSanPham;
    private String HinhAnh;
    private String MoTa;
    private int IdLoai;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public Integer getGiaSanPham() {
        return GiaSanPham;
    }

    public void setGiaSanPham(Integer giaSanPham) {
        GiaSanPham = giaSanPham;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public int getIdLoai() {
        return IdLoai;
    }

    public void setIdLoai(int idLoai) {
        IdLoai = idLoai;
    }


    public Sanpham(int id, String tenSanPham, Integer giaSanPham, String hinhAnh, String moTa, int idLoai) {
        Id = id;
        TenSanPham = tenSanPham;
        GiaSanPham = giaSanPham;
        HinhAnh = hinhAnh;
        MoTa = moTa;
        IdLoai = idLoai;
    }

}
