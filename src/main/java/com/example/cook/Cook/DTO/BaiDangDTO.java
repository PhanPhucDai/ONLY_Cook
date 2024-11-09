package com.example.cook.Cook.DTO;

import com.example.cook.Cook.Entity.NguoiDung;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;

public class BaiDangDTO {

    private String noiDungBaiDang;
    private LocalDateTime ngayBaiDang;
    private String hinhAnh;
    private String tenNguoiDung;
    private String hinhAnhNguoiDung;
    private String thoiGianBatDauDangBai;

    public BaiDangDTO( String tenNguoiDung, String hinhAnhNguoiDung,String noiDungBaiDang, String hinhAnh, LocalDateTime ngayBaiDang) {
        this.noiDungBaiDang = noiDungBaiDang;
        this.tenNguoiDung = tenNguoiDung;
        this.hinhAnh = hinhAnh;
        this.ngayBaiDang = ngayBaiDang;
        this.hinhAnhNguoiDung = hinhAnhNguoiDung;
    }

    public BaiDangDTO() {
    }

    public BaiDangDTO(String thoiGianBatDauDangBai, String tenNguoiDung, String hinhAnh, String noiDungBaiDang) {
        this.thoiGianBatDauDangBai = thoiGianBatDauDangBai;
        this.tenNguoiDung = tenNguoiDung;
        this.hinhAnh = hinhAnh;
        this.noiDungBaiDang = noiDungBaiDang;
    }

    public String getThoiGianBatDauDangBai() {
        return thoiGianBatDauDangBai;
    }

    public void setThoiGianBatDauDangBai(String thoiGianBatDauDangBai) {
        this.thoiGianBatDauDangBai = thoiGianBatDauDangBai;
    }

    public String getHinhAnhNguoiDung() {
        return hinhAnhNguoiDung;
    }

    public void setHinhAnhNguoiDung(String hinhAnhNguoiDung) {
        this.hinhAnhNguoiDung = hinhAnhNguoiDung;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public LocalDateTime  getNgayBaiDang() {
        return ngayBaiDang;
    }

    public void setNgayBaiDang(LocalDateTime  ngayBaiDang) {
        this.ngayBaiDang = ngayBaiDang;
    }


    public String getNoiDungBaiDang() {
        return noiDungBaiDang;
    }

    public void setNoiDungBaiDang(String noiDungBaiDang) {
        this.noiDungBaiDang = noiDungBaiDang;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }
}
