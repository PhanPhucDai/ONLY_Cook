package com.example.cook.Cook.Dao;

import com.example.cook.Cook.DTO.CongThucDTO;
import com.example.cook.Cook.Entity.DanhSachYeuThich;
import com.example.cook.Cook.Entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "DanhSachYeuThich" , exported = false)
public interface Danhsachyeuthich extends JpaRepository<DanhSachYeuThich, Integer> {
    @Query("SELECT ds FROM DanhSachYeuThich ds WHERE ds.maNguoiDung = :maNguoiDung")
    List<DanhSachYeuThich> findAllByMaNguoiDung(@Param("maNguoiDung") NguoiDung maNguoiDung);

    DanhSachYeuThich findById(@Param("maDanhSachYeuThich") int maDanhSachYeuThich);
    @Query("SELECT new com.example.cook.Cook.DTO.CongThucDTO(ct.maCongThuc, ct.tenCongThuc, ct.moTa, nd.tenNguoiDung, ct.diemDanhGiaTrungBinh) " +
            "FROM CongThuc ct " +
            "JOIN ct.danhSachYeuThich dsyt " +
            "JOIN dsyt.maNguoiDung nd " +
            "WHERE dsyt.maDanhSachYeuThich = :idDanhSach")
    List<CongThucDTO> findCongThucByIdDanhSach(@Param("idDanhSach") int idDanhSach);




}
