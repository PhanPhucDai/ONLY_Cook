package com.example.cook.Cook.Entity;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "LoaiMonAn")
@Entity
public class LoaiMonAn {
    @Id
    @Column(name = "maLoaiMonAn")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maNguyenLieu;

    @Column(name = "tenLoaiMonAn")
    private String tenNguyenLieu;

    @Column(name = "hinhAnh")
    private String hinhAnh;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "maLoaiMonAn")
    private List<CongThuc> congThucList;



    public LoaiMonAn() {
    }

    public LoaiMonAn(String hinhAnh, String tenNguyenLieu, int maNguyenLieu) {
        this.hinhAnh = hinhAnh;
        this.tenNguyenLieu = tenNguyenLieu;
        this.maNguyenLieu = maNguyenLieu;
    }

    public int getMaNguyenLieu() {
        return maNguyenLieu;
    }

    public void setMaNguyenLieu(int maNguyenLieu) {
        this.maNguyenLieu = maNguyenLieu;
    }

    public String getTenNguyenLieu() {
        return tenNguyenLieu;
    }

    public void setTenNguyenLieu(String tenNguyenLieu) {
        this.tenNguyenLieu = tenNguyenLieu;
    }
}
