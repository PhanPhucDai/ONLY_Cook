package com.example.cook.Cook.Entity;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "NguoiDung")
@Entity
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maNguoiDung")
    private int maNguoiDung;

    @Column(name = "tenNguoiDung")
    private String tenNguoiDung;

    @Column(name = "diaChiEmail")
    private String diaChiEmail;

    @Column(name = "tenDangNhap")
    private String tenDangNhap;

    @Column(name = "vaiTro")
    private String vaiTro;

    @Column(name = "hinhAnh")
    private String hinhAnh;

    @Column(name = "matKhau")
    private String matKhau;

    @Column(name = "enable")
    private String enable;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "maNguoiDung")
    private List<DanhSachYeuThich> danhSachYeuThichList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "maNguoiDung")
    private List<CongThuc> maCongThucList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "maNguoiDung")
    private List<BaiDang> baiDangList;

    public NguoiDung(String enable, String matKhau, String hinhAnh, String vaiTro, String tenDangNhap, String diaChiEmail, String tenNguoiDung) {
        this.enable = enable;
        this.matKhau = matKhau;
        this.hinhAnh = hinhAnh;
        this.vaiTro = vaiTro;
        this.tenDangNhap = tenDangNhap;
        this.diaChiEmail = diaChiEmail;
        this.tenNguoiDung = tenNguoiDung;
    }

    public NguoiDung() {
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getDiaChiEmail() {
        return diaChiEmail;
    }

    public void setDiaChiEmail(String diaChiEmail) {
        this.diaChiEmail = diaChiEmail;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
