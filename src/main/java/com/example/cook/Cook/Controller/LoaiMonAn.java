package com.example.cook.Cook.Controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.cook.Cook.Dao.CongThucDao;
import com.example.cook.Cook.Entity.NguoiDung;
import com.example.cook.Cook.Service.CongThucService;
import com.example.cook.Cook.Service.DanhSachYeuThichService;
import com.example.cook.Cook.Service.NguoiDungService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import com.example.cook.Cook.DTO.CongThucDTO;
import com.example.cook.Cook.Entity.CongThuc;
import com.example.cook.Cook.Service.LoaiMonAnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoaiMonAn {
    @Autowired
    private CongThucService congThucService;
    @Autowired
    private NguoiDungService nguoiDungService;
    @Autowired
    private CongThucDao congThucDao;
    @Autowired
    private DanhSachYeuThichService danhSachYeuThichService;
    @Autowired
    private LoaiMonAnService loaiMonAnService;
    @GetMapping("/Cong-thuc-theo-loai/{id}")
    public String congThucList(@PathVariable("id")int id, Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String tenDangNhap=authentication.getName();
        NguoiDung nguoiDung=nguoiDungService.getNguoiDung(tenDangNhap);
        List<CongThucDTO> congThucList=loaiMonAnService.getAllCongthucByLoaiMonAn(id);
        List<com.example.cook.Cook.Entity.LoaiMonAn> loaiMonAnList =loaiMonAnService.loaiMonAnlist();
        model.addAttribute("loaiMonAnList",loaiMonAnList );
        model.addAttribute("listCongThuc",congThucList);
        model.addAttribute("nguoiDung", nguoiDung.getTenNguoiDung());
        model.addAttribute("dsYeuThich", danhSachYeuThichService.getDanhSachYeuThich());

        return "/CongthucTheoLoaiMonAn";
    }
}
