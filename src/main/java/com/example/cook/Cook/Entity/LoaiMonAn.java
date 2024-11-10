package com.example.cook.Cook.Entity;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "LoaiMonAn")
@Entity
public class LoaiMonAn {
    @Id
    @Column(name = "maLoaiMonAn")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maLoaiMonAn;

    @Column(name = "tenLoaiMonAn")
    private String tenLoaiMonAn;



    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "maLoaiMonAn")
    private List<CongThuc> congThucList;



    public LoaiMonAn() {
    }

    public LoaiMonAn(  String tenLoaiMonAn, int maLoaiMonAn) {

        this.tenLoaiMonAn = tenLoaiMonAn;
        this.maLoaiMonAn = maLoaiMonAn;
    }

    public List<CongThuc> getCongThucList() {
        return congThucList;
    }

    public void setCongThucList(List<CongThuc> congThucList) {
        this.congThucList = congThucList;
    }

    public String getTenLoaiMonAn() {
        return tenLoaiMonAn;
    }

    public void setTenLoaiMonAn(String tenLoaiMonAn) {
        this.tenLoaiMonAn = tenLoaiMonAn;
    }

    public int getMaLoaiMonAn() {
        return maLoaiMonAn;
    }

    public void setMaLoaiMonAn(int maLoaiMonAn) {
        this.maLoaiMonAn = maLoaiMonAn;
    }
}
