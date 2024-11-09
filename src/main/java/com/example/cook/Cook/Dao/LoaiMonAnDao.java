package com.example.cook.Cook.Dao;


import com.example.cook.Cook.Entity.LoaiMonAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "LoaiMonAn" , exported = false)
public interface LoaiMonAnDao extends JpaRepository<LoaiMonAn, Integer> {
    LoaiMonAn findById(@Param("maLoaiMonAn")int maLoaiMonAn);
}

