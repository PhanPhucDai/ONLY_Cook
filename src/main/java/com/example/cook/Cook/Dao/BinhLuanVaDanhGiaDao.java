package com.example.cook.Cook.Dao;


import com.example.cook.Cook.Entity.BinhluanVaDanhgia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="binhluanvadanhgia", exported = false)
public interface BinhLuanVaDanhGiaDao extends JpaRepository<BinhluanVaDanhgia,Integer > {
}
