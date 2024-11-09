package com.example.cook.Cook.Entity;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Table(name = "CongThuc")
@Entity
public class CongThuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maCongThuc")
    private int maCongThuc;

    @Column(name = "tenCongThuc")
    private String tenCongThuc;

    @Column(name = "moTa")
    private String moTa;

    @Column(name = "videoURL")
    private String videoURL;

    @Column(name = "hinhAnh")
    private String hinhAnh;

    @Column(name = "cacBuocThucHien")
    private String cacBuocThucHien;

    @Column(name = "diemDanhGiaTrungBinh")
    private String diemDanhGiaTrungBinh;

    @Column(name = "nguyenLieu")
    private String nguyenLieu;

    @ManyToOne
    @JoinColumn(name="maLoaiMonAn")
    private LoaiMonAn maLoaiMonAn;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "maCongThuc")
    private List<BinhluanVaDanhgia> binhluanVaDanhgiaList;

    @ManyToOne()
    @JoinColumn(name="maNguoiDung")
    private NguoiDung maNguoiDung;


    @ManyToMany(mappedBy = "congThuc")
    private List<DanhSachYeuThich> danhSachYeuThich;
    public CongThuc() {
    }




    public CongThuc(String tenCongThuc, String moTa, String resultHinhAnh, String videoURL, String cacBuocThucHien, String diemDanhGiaTrungBinh, String nguyenLieu) {
        this.tenCongThuc = tenCongThuc;
        this.moTa = moTa;
        this.hinhAnh = resultHinhAnh;
        this.videoURL = videoURL;
        this.cacBuocThucHien = cacBuocThucHien;
        this.diemDanhGiaTrungBinh = diemDanhGiaTrungBinh;
        this.nguyenLieu = nguyenLieu;

    }

    public CongThuc(String tenCongThuc, String moTa, String hinhAnh, String videoURL, String cacBuocThucHien, String diemDanhGiaTrungBinh, String nguyenLieu,LoaiMonAn loaiMonAn, NguoiDung nguoiDung) {
        this.maNguoiDung=nguoiDung;
        this.maLoaiMonAn = loaiMonAn;
        this.nguyenLieu = nguyenLieu;
        this.diemDanhGiaTrungBinh = diemDanhGiaTrungBinh;
        this.cacBuocThucHien = cacBuocThucHien;
        this.hinhAnh = hinhAnh;
        this.videoURL = videoURL;
        this.moTa = moTa;
        this.tenCongThuc = tenCongThuc;
    }


    public String getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(String nguyenLieu) {
        this.nguyenLieu = nguyenLieu;
    }

    public List<BinhluanVaDanhgia> getBinhluanVaDanhgiaList() {
        return binhluanVaDanhgiaList;
    }

    public void setBinhluanVaDanhgiaList(List<BinhluanVaDanhgia> binhluanVaDanhgiaList) {
        this.binhluanVaDanhgiaList = binhluanVaDanhgiaList;
    }

    public int getMaCongThuc() {
        return maCongThuc;
    }

    public void setMaCongThuc(int maCongThuc) {
        this.maCongThuc = maCongThuc;
    }

    public String getDiemDanhGiaTrungBinh() {
        return diemDanhGiaTrungBinh;
    }

    public void setDiemDanhGiaTrungBinh(String diemDanhGiaTrungBinh) {
        this.diemDanhGiaTrungBinh = diemDanhGiaTrungBinh;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getCacBuocThucHien() {
        return cacBuocThucHien;
    }

    public void setCacBuocThucHien(String cacBuocThucHien) {
        this.cacBuocThucHien = cacBuocThucHien;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenCongThuc() {
        return tenCongThuc;
    }

    public void setTenCongThuc(String tenCongThuc) {
        this.tenCongThuc = tenCongThuc;
    }
}
