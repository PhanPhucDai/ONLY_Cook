package com.example.cook.Cook.Dao;

import com.example.cook.Cook.DTO.BaiDangDTO;
import com.example.cook.Cook.Entity.BaiDang;
import com.example.cook.Cook.Entity.NguoiDung;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "baidang", exported = false)
public interface  BaiDangDao extends JpaRepository<BaiDang, Integer> {

    @Query("select new com.example.cook.Cook.DTO.BaiDangDTO( nd.tenNguoiDung, nd.hinhAnh, bd.noiDungBaiDang, bd.hinhAnh, bd.ngayBaiDang) " +
            "from BaiDang bd " +
            "join bd.maNguoiDung nd " +
            "where bd.cheDoXem = 1")
    List<BaiDangDTO> AllBaiDang();

    @Query("select new com.example.cook.Cook.DTO.BaiDangDTO( nd.tenNguoiDung, nd.hinhAnh,  bd.noiDungBaiDang, bd.hinhAnh, bd.ngayBaiDang) " +
            "from BaiDang bd " +
            "join bd.maNguoiDung nd " +
            "where bd.cheDoXem = 1 " +
            "and nd.maNguoiDung= :maSoNguoiDung")
    List<BaiDangDTO> findAllByMaNguoiDung(@Param("maSoNguoiDung") int maSoNguoiDung);
}
