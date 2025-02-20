package com.example.cook.Cook.Service;

import com.example.cook.Cook.Dao.BaiDangDao;
import com.example.cook.Cook.Dao.NguoiDungDao;
import com.example.cook.Cook.Entity.BaiDang;
import com.example.cook.Cook.Entity.NguoiDung;
import com.example.cook.Cook.security.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class NguoiDungService {
    @Autowired
    private NguoiDungDao nguoiDungDao;

    @Transactional
    public NguoiDung getNguoiDung(String tenDangNhap){
        NguoiDung nguoiDung= nguoiDungDao.findByTenDangNhap(tenDangNhap);
        return nguoiDung;
    }
    @Transactional
    public NguoiDung update_tenNguoiDUng(String tenTaiKhoan){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String tenDangNhap=authentication.getName();
        NguoiDung nguoiDung=getNguoiDung(tenDangNhap);
        nguoiDung.setTenDangNhap(tenTaiKhoan);
        try {
            nguoiDungDao.save(nguoiDung);
            Authentication newAuth = new UsernamePasswordAuthenticationToken(
                    nguoiDung.getTenDangNhap(),
                    authentication.getCredentials(),
                    authentication.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(newAuth);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nguoiDung;
    }

    @Transactional
    public NguoiDung update_diaChi(String diaChi) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String tenDangNhap=authentication.getName();
        NguoiDung nguoiDung=getNguoiDung(tenDangNhap);
        nguoiDung.setDiaChiEmail(diaChi);
        nguoiDungDao.save(nguoiDung);
        return nguoiDung;
    }

    @Transactional
    public NguoiDung update_hinhAnh(String hinhAnh){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String tenDangNhap=authentication.getName();
        NguoiDung nguoiDung=getNguoiDung(tenDangNhap);
        nguoiDung.setHinhAnh(hinhAnh);
        nguoiDungDao.save(nguoiDung);
        return nguoiDung;
    }

    @Transactional
    public NguoiDung update_matKhau(String matKhauMoi){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String tenDangNhap=authentication.getName();
        NguoiDung nguoiDung=getNguoiDung(tenDangNhap);
        nguoiDung.setMatKhau(matKhauMoi) ;
        nguoiDungDao.save(nguoiDung);
        return nguoiDung;
    }


   // enable,   matKhau,   hinhAnh,   vaiTro,   tenDangNhap,   diaChiEmail,   tenNguoiDung) {


        @Transactional
    public boolean taoNguoiDung(String tenDangNhap, String email, String matKhau, String hoVaTen) {

        NguoiDung nguoiDung=new NguoiDung("1",   "{noop}"+matKhau,   "people(1).png",   "user",   tenDangNhap,   email,   hoVaTen);
            try {
                nguoiDungDao.save(nguoiDung);
                return true;
            } catch (Exception e) {
                return false;
            }

    }

}
