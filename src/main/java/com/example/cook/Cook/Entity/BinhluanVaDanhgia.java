package com.example.cook.Cook.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "BinhluanVaDanhgia")
public class BinhluanVaDanhgia {

    @Id
    @Column(name = "maBinhLuan")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maBinhLuan;

    @Column(name = "noiDungBinhLuan")
    private String noiDungBinhLuan;

    @Column(name = "ketQuaDanhGia")
    private int ketQuaDanhGia;

    @Column(name = "ngayBinhLuan")
    private Date ngayBinhLuan;

    @ManyToOne()
    @JoinColumn(name="maBaiDang")
    private BaiDang maBaiDang;

    @ManyToOne()
    @JoinColumn(name="maCongThuc")
    private CongThuc  maCongThuc;

    public BinhluanVaDanhgia(int maBinhLuan, Date ngayBinhLuan, String noiDungBinhLuan, int ketQuaDanhGia) {
        this.maBinhLuan = maBinhLuan;
        this.ngayBinhLuan = ngayBinhLuan;
        this.noiDungBinhLuan = noiDungBinhLuan;
        this.ketQuaDanhGia = ketQuaDanhGia;
    }

    public BinhluanVaDanhgia() {
    }

    public int getKetQuaDanhGia() {
        return ketQuaDanhGia;
    }

    public void setKetQuaDanhGia(int ketQuaDanhGia) {
        this.ketQuaDanhGia = ketQuaDanhGia;
    }

    public Date getNgayBinhLuan() {
        return ngayBinhLuan;
    }

    public void setNgayBinhLuan(Date ngayBinhLuan) {
        this.ngayBinhLuan = ngayBinhLuan;
    }

    public String getNoiDungBinhLuan() {
        return noiDungBinhLuan;
    }

    public void setNoiDungBinhLuan(String noiDungBinhLuan) {
        this.noiDungBinhLuan = noiDungBinhLuan;
    }

    public int getMaBinhLuan() {
        return maBinhLuan;
    }

    public void setMaBinhLuan(int maBinhLuan) {
        this.maBinhLuan = maBinhLuan;
    }
}
