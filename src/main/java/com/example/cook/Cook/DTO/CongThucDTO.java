package com.example.cook.Cook.DTO;

public class CongThucDTO {
    private int maCongThuc;
    private String tenCongThuc;
    private String diemTB;
    private String tenNguoiTao;
    private String moTa;

    public CongThucDTO(int maCongThuc, String tenCongThuc, String moTa, String tenNguoiTao, String diemTB) {
        this.maCongThuc = maCongThuc;
        this.tenCongThuc = tenCongThuc;
        this.moTa = moTa;
        this.tenNguoiTao = tenNguoiTao;
        this.diemTB = diemTB;
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
