package com.example.cook.Cook.Service;

import com.example.cook.Cook.Dao.LoaiMonAnDao;
import com.example.cook.Cook.Entity.LoaiMonAn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiMonAnService {
    @Autowired
    private LoaiMonAnDao loaiMonAnDao;

    public List<LoaiMonAn> loaiMonAnlist(){
        List<LoaiMonAn> loaiMonAns=loaiMonAnDao.findAll();
        return  loaiMonAns;
}
}
