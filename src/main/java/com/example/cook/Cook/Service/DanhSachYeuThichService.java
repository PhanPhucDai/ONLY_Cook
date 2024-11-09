package com.example.cook.Cook.Service;

import com.example.cook.Cook.DTO.ChiTietCongThucDTO;
import com.example.cook.Cook.DTO.CongThucDTO;
import com.example.cook.Cook.Dao.Danhsachyeuthich;
import com.example.cook.Cook.Entity.CongThuc;
import com.example.cook.Cook.Entity.DanhSachYeuThich;
import com.example.cook.Cook.Entity.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DanhSachYeuThichService {
    @Autowired
    private Danhsachyeuthich danhsachyeuthich;
    @Autowired
    private NguoiDungService nguoiDungService;
    @Autowired
    private CongThucService congThucService;

    @Transactional
    public List<DanhSachYeuThich> getDanhSachYeuThich(){
        Authentication authorization= SecurityContextHolder.getContext().getAuthentication();
        NguoiDung nguoiDung=nguoiDungService.getNguoiDung(authorization.getName());
        List<DanhSachYeuThich> danhSachYeuThichList=danhsachyeuthich.findAllByMaNguoiDung(nguoiDung);
        return danhSachYeuThichList;
    }

    @Transactional
    public List<CongThucDTO> layDanhSachCongThuc(int madsyeu_thich){
            DanhSachYeuThich danhSachYeuThichList=danhsachyeuthich.findById(madsyeu_thich);
            List<CongThucDTO> congThucList=danhsachyeuthich.findCongThucByIdDanhSach(madsyeu_thich);
        return congThucList;
    }

    @Transactional
    public DanhSachYeuThich layTenDanhSachCongThuc(int madsyeu_thich){
        DanhSachYeuThich danhSachYeuThichList=danhsachyeuthich.findById(madsyeu_thich);
        return danhSachYeuThichList;
    }


    @Transactional
    public int themYeuThich(int madsyeu_thich, int maCongThuc){
        try {
            CongThuc congThuc = convertCongThuc(congThucService.getCongThucById(maCongThuc));
            DanhSachYeuThich danhSachYeuThichList = danhsachyeuthich.findById(madsyeu_thich);


            for (CongThuc ct : danhSachYeuThichList.getCongThuc()) {
                if (ct.getMaCongThuc()== maCongThuc) {
                    return -1;
                }
            }


            danhSachYeuThichList.getCongThuc().add(congThuc);
            danhsachyeuthich.save(danhSachYeuThichList); // Lưu vào cơ sở dữ liệu
        } catch (DataIntegrityViolationException data) {
            return -1; // Lỗi vi phạm ràng buộc dữ liệu
        } catch (Exception e) {
            return 0; // Lỗi khác
        }
        return 1; // Thành công
    }

    @Transactional
    public int themYeuThich(DanhSachYeuThich danhSachYeuThich){
        try {
            danhsachyeuthich.save(danhSachYeuThich);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Transactional
    public int xoaDanhSachYeuThich(int maDanhSachYeuThich){
        try {
            DanhSachYeuThich danhSachYeuThich=new DanhSachYeuThich();

            DanhSachYeuThich congThucList= danhsachyeuthich.findById(maDanhSachYeuThich);
            congThucList.getCongThuc().clear();

            danhsachyeuthich.deleteById(maDanhSachYeuThich);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public CongThuc convertCongThuc(ChiTietCongThucDTO  congThucDTO) {
        CongThuc congThuc=new CongThuc();
        congThuc.setMaCongThuc(congThucDTO.getMaCongThuc());
        congThuc.setTenCongThuc(congThucDTO.getTenCongThuc());
        congThuc.setMoTa(congThucDTO.getMoTa());
        congThuc.setDiemDanhGiaTrungBinh(congThucDTO.getDiemTB());
        congThuc.setCacBuocThucHien(congThucDTO.getCacBuocThucHien());

        return congThuc;
    }
}
