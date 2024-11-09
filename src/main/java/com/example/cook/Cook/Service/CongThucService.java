package com.example.cook.Cook.Service;

import com.example.cook.Cook.DTO.ChiTietCongThucDTO;
import com.example.cook.Cook.DTO.CongThucDTO;
import com.example.cook.Cook.Dao.CongThucDao;
import com.example.cook.Cook.Dao.LoaiMonAnDao;
import com.example.cook.Cook.Entity.CongThuc;
import com.example.cook.Cook.Entity.LoaiMonAn;
import com.example.cook.Cook.Entity.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CongThucService {
    @Autowired
    private   CongThucDao congThucDao;
    @Autowired
    private NguoiDungService NguoiDungService;
    @Autowired
    private LoaiMonAnDao loaiMonAnDao;


    @Transactional
    public void themCongThuc(CongThuc congThuc,int idLoaiMonAn) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nameDangNhap = authentication.getName();
        NguoiDung nguoiDung = NguoiDungService.getNguoiDung(nameDangNhap);
        LoaiMonAn loaiMonAn = loaiMonAnDao.findById(idLoaiMonAn);
        CongThuc congThuc1 = new CongThuc(congThuc.getTenCongThuc()
                , congThuc.getMoTa()
                , congThuc.getHinhAnh()
                , congThuc.getVideoURL()
                , congThuc.getCacBuocThucHien()
                , congThuc.getDiemDanhGiaTrungBinh()
                , congThuc.getNguyenLieu()
                , loaiMonAn
                , nguoiDung

        );
        congThucDao.save(congThuc1);

    }

    @Transactional
    public List<CongThucDTO> getAllCongThuc(){
        return congThucDao.getcongThucList() ;
    }
    @Transactional
    public ChiTietCongThucDTO getCongThucById(int maCongThuc){
        return congThucDao.getCongThucById(maCongThuc) ;
    }
}
