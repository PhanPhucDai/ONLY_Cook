package com.example.cook.Cook.Service;

import com.example.cook.Cook.DTO.CongThucDTO;
import com.example.cook.Cook.Dao.CongThucDao;
import com.example.cook.Cook.Dao.LoaiMonAnDao;
import com.example.cook.Cook.Entity.CongThuc;
import com.example.cook.Cook.Entity.LoaiMonAn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiMonAnService {
    @Autowired
    private LoaiMonAnDao loaiMonAnDao;
    @Autowired
    private CongThucDao congThucDao;

    public List<LoaiMonAn> loaiMonAnlist(){
        List<LoaiMonAn> loaiMonAns=loaiMonAnDao.findAll();
        return  loaiMonAns;
}

    public List<CongThucDTO> getAllCongthucByLoaiMonAn(int id) {
        LoaiMonAn loaiMonAn=loaiMonAnDao.findById(id);
        List<CongThucDTO> listCongThuc=congThucDao.getAllcongThucListByLoaimonan(loaiMonAn);
        return listCongThuc;
    }
}
