package com.example.cook.Cook.Entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Table(name = "DanhSachYeuThich")
@Entity
public class DanhSachYeuThich {

    @Id
    @Column(name = "maDanhSachYeuThich")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maDanhSachYeuThich;

    @Column(name = "ngayThem")
    private Date ngayThem;

    @Column(name = "tenDanhSachYeuThich")
    private String tenDanhSachYeuThich;



    @ManyToOne()
    @JoinColumn(name="ma_nguoi_dung")
    private NguoiDung maNguoiDung;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "DSYeuThich_CongThuc",
                joinColumns = @JoinColumn(name="MaDSYeuThich"),
                inverseJoinColumns =@JoinColumn(name="MacongThuc"))
    private List<CongThuc> congThuc;

    public DanhSachYeuThich() {
    }

    public DanhSachYeuThich(Date ngayThem, String tenDanhSachYeuThich, NguoiDung maNguoiDung) {
        this.ngayThem = ngayThem;
        this.tenDanhSachYeuThich = tenDanhSachYeuThich;
        this.maNguoiDung = maNguoiDung;
    }

    public NguoiDung getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(NguoiDung maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public List<CongThuc> getCongThuc() {
        return congThuc;
    }

    public void setCongThuc(List<CongThuc> congThuc) {
        this.congThuc = congThuc;
    }

    public int getMaDanhSachYeuThich() {
        return maDanhSachYeuThich;
    }

    public void setMaDanhSachYeuThich(int maDanhSachYeuThich) {
        this.maDanhSachYeuThich = maDanhSachYeuThich;
    }


    public String getTenDanhSachYeuThich() {
        return tenDanhSachYeuThich;
    }

    public void setTenDanhSachYeuThich(String tenDanhSachYeuThich) {
        this.tenDanhSachYeuThich = tenDanhSachYeuThich;
    }

    public Date getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(Date ngayThem) {
        this.ngayThem = ngayThem;
    }
}
