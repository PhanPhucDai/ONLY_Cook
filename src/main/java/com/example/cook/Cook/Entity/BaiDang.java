package com.example.cook.Cook.Entity;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "BaiDang")
public class BaiDang {

    @Id
    @Column(name = "maBaiDang")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maBaiDang;

    @Column(name = "noiDungBaiDang")
    private String noiDungBaiDang;

    @Column(name = "cheDoXem")
    private int cheDoXem;

    @Column(name = "ngayBaiDang")
    private LocalDateTime ngayBaiDang;

    @Column(name = "hinhAnh")
    private String hinhAnh;

    @Column(name = "video")
    private String video;

    @ManyToOne()
    @JoinColumn(name="maNguoiDung")
    private NguoiDung maNguoiDung;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "maBaiDang")
    private List<BinhluanVaDanhgia> binhluanVaDanhgiaList;



    public BaiDang() {
    }


    public BaiDang(NguoiDung maNguoiDung, String noiDungBaiDang, int cheDoXem, String hinhAnh) {
        this.maNguoiDung = maNguoiDung;
        this.cheDoXem =  cheDoXem;
        this.hinhAnh = hinhAnh;
        this.noiDungBaiDang = noiDungBaiDang;
    }

    public BaiDang(String noiDungBaiDang, int cheDoXem, LocalDateTime ngayBaiDang, String hinhAnh, String video, NguoiDung maNguoiDung) {
        this.noiDungBaiDang = noiDungBaiDang;
        this.cheDoXem = cheDoXem;
        this.ngayBaiDang = ngayBaiDang;
        this.hinhAnh =  hinhAnh;
        this.video = video;
        this.maNguoiDung = maNguoiDung;
    }

    public int getMaBaiDang() {
        return maBaiDang;
    }

    public void setMaBaiDang(int maBaiDang) {
        this.maBaiDang = maBaiDang;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public LocalDateTime getNgayBaiDang() {
        return ngayBaiDang;
    }

    public void setNgayBaiDang(LocalDateTime ngayBaiDang) {
        this.ngayBaiDang = ngayBaiDang;
    }



    public String getNoiDungBaiDang() {
        return noiDungBaiDang;
    }

    public void setNoiDungBaiDang(String noiDungBaiDang) {
        this.noiDungBaiDang = noiDungBaiDang;
    }
}
