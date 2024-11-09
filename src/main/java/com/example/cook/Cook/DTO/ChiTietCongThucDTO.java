package com.example.cook.Cook.DTO;

public class ChiTietCongThucDTO {
    private int maCongThuc;
    private String tenCongThuc;
    private String diemTB;
    private String tenNguoiTao;
    private String moTa;
    private String hinhAnh;
    private String cacBuocThucHien;
    private String nguyenLieu;
    private String videoURL;

    public ChiTietCongThucDTO(int maCongThuc, String tenCongThuc, String moTa, String tenNguoiTao, String diemTB,String videoURL, String hinhAnh , String nguyenLieu, String cacBuocThucHien) {
        this.maCongThuc = maCongThuc;
        this.videoURL = videoURL;
        this.nguyenLieu = nguyenLieu;
        this.cacBuocThucHien = cacBuocThucHien;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.tenNguoiTao = tenNguoiTao;
        this.tenCongThuc = tenCongThuc;
        this.diemTB = diemTB;
    }

    public String getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(String nguyenLieu) {
        this.nguyenLieu = nguyenLieu;
    }

    public String getCacBuocThucHien() {
        return cacBuocThucHien;
    }

    public void setCacBuocThucHien(String cacBuocThucHien) {
        this.cacBuocThucHien = cacBuocThucHien;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public int getMaCongThuc() {
        return maCongThuc;
    }

    public void setMaCongThuc(int maCongThuc) {
        this.maCongThuc = maCongThuc;
    }


    public String getTenCongThuc() {
        return tenCongThuc;
    }

    public void setTenCongThuc(String tenCongThuc) {
        this.tenCongThuc = tenCongThuc;
    }

    public String getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(String diemTB) {
        this.diemTB = diemTB;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenNguoiTao() {
        return tenNguoiTao;
    }

    public void setTenNguoiTao(String tenNguoiTao) {
        this.tenNguoiTao = tenNguoiTao;
    }
}
