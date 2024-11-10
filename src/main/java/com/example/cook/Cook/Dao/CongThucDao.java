package com.example.cook.Cook.Dao;

import com.example.cook.Cook.DTO.ChiTietCongThucDTO;
import com.example.cook.Cook.DTO.CongThucDTO;
import com.example.cook.Cook.Entity.CongThuc;
import com.example.cook.Cook.Entity.LoaiMonAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.core.parameters.P;

import java.util.List;

@RepositoryRestResource(path="CongThuc" , exported = false)
public interface CongThucDao extends JpaRepository<CongThuc,Integer >  {
    @Query("SELECT new com.example.cook.Cook.DTO.CongThucDTO(ct.maCongThuc, ct.tenCongThuc, ct.moTa, nd.tenNguoiDung, ct.diemDanhGiaTrungBinh) " +
            "FROM CongThuc ct JOIN ct.maNguoiDung nd")
    List<CongThucDTO> getcongThucList();

    @Query("SELECT new com.example.cook.Cook.DTO.CongThucDTO(ct.maCongThuc, ct.tenCongThuc, ct.moTa, nd.tenNguoiDung, ct.diemDanhGiaTrungBinh) " +
            "FROM CongThuc ct " +
            "JOIN ct.maNguoiDung nd " +
            "Where ct.maLoaiMonAn = :maLoaiMonAn")
    List<CongThucDTO> getAllcongThucListByLoaimonan(@Param("maLoaiMonAn") LoaiMonAn maLoaiMonAn);

    @Query("SELECT new com.example.cook.Cook.DTO.ChiTietCongThucDTO(ct.maCongThuc, ct.tenCongThuc, ct.moTa, nd.tenNguoiDung, ct.diemDanhGiaTrungBinh, ct.videoURL, ct.hinhAnh, ct.nguyenLieu, ct.cacBuocThucHien) " +
            "FROM CongThuc ct JOIN ct.maNguoiDung nd " +
            "WHERE ct.maCongThuc = :maCongThuc")
    ChiTietCongThucDTO getCongThucById(@Param("maCongThuc") int maCongThuc);

    @Query("SELECT new com.example.cook.Cook.DTO.ChiTietCongThucDTO(ct.maCongThuc, ct.tenCongThuc, ct.moTa, nd.tenNguoiDung, ct.diemDanhGiaTrungBinh, ct.videoURL, ct.hinhAnh, ct.nguyenLieu, ct.cacBuocThucHien) " +
            "FROM CongThuc ct JOIN ct.maNguoiDung nd " +
            "WHERE ct.tenCongThuc Like :tenCongThuc")
    ChiTietCongThucDTO getCongThucByInput(@Param("tenCongThuc") String tenCongThuc);


}
