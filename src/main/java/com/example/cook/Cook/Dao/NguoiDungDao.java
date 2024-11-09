package com.example.cook.Cook.Dao;

import com.example.cook.Cook.Entity.LoaiMonAn;
import com.example.cook.Cook.Entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "NguoiDung" , exported = false)
public interface NguoiDungDao  extends JpaRepository<NguoiDung, Integer> {
    NguoiDung findByTenDangNhap(String ten_dang_nhap);

}
