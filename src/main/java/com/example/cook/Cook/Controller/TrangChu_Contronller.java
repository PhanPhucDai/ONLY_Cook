
package com.example.cook.Cook.Controller;

import com.example.cook.Cook.DTO.BaiDangDTO;
import com.example.cook.Cook.Entity.BaiDang;
import com.example.cook.Cook.Entity.LoaiMonAn;
import com.example.cook.Cook.Entity.NguoiDung;
import com.example.cook.Cook.Service.BaiDangService;
import com.example.cook.Cook.Service.LoaiMonAnService;
import com.example.cook.Cook.Service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class TrangChu_Contronller {
    @Autowired
    private BaiDangService baiDangService;
    @Autowired
    private NguoiDungService nguoiDung;
    @Autowired
    private LoaiMonAnService loaiMonAnService;

    @GetMapping("/Trang-chu")
    public String trangChu(Model model
            , @RequestParam(value = "errol", required = false) String errol
            , @RequestParam(value = "success", required = false) String success) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String tenDangNhap = authentication.getName();
        NguoiDung nguoidung = nguoiDung.getNguoiDung(tenDangNhap);
        List<BaiDangDTO> baiDangDTOS = listTime(baiDangService.baiDangDTOList());
        List<LoaiMonAn> loaiMonAnList =loaiMonAnService.loaiMonAnlist();
        model.addAttribute("loaiMonAnList",loaiMonAnList );
        model.addAttribute("errol", errol);
        model.addAttribute("success", success);
        model.addAttribute("baiDangs", baiDangDTOS);
        model.addAttribute("nguoiDung", nguoidung.getTenNguoiDung());
        return "Trang_chu";
    }

    @PostMapping("/luu-bai-dang")
    public String luuBaiDang(Model model
            , @RequestParam(value = "errol", required = false) String errol
            , @RequestParam(value = "success", required = false) String success
            , @RequestParam(value = "hinhAnh", required = false) MultipartFile hinhAnh
            , @RequestParam(value = "noiDungBaiDang") String noiDungBaiDang
            , @RequestParam(value = "cheDoXem") boolean cheDoXem
            , RedirectAttributes redirectAttributes) throws IOException {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String tenDangNhap = authentication.getName();
        NguoiDung nguoidung = nguoiDung.getNguoiDung(tenDangNhap);
        byte[] byteHinhAnh = hinhAnh.getBytes();
        String base64HinhAnh = Base64.getEncoder().encodeToString(byteHinhAnh);
        BaiDang baiDang = new BaiDang(nguoidung,noiDungBaiDang, (cheDoXem ?1:0), "data:image/png;base64,"+base64HinhAnh);
        baiDangService.luuBaiDang(baiDang);
        return "redirect:/ban_tin_ca_nhan";
    }

    public List listTime(List<BaiDangDTO> danhSachBaiDang) {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<BaiDangDTO> baiDangDTOS = new ArrayList<>();
        for (BaiDangDTO baidang : danhSachBaiDang) {
            long soNgayChenhLech = ChronoUnit.DAYS.between(baidang.getNgayBaiDang(), localDateTime);
            long soGioChenhLech = ChronoUnit.HOURS.between(baidang.getNgayBaiDang(), localDateTime);
            long soThangChenhLech = ChronoUnit.MONTHS.between(baidang.getNgayBaiDang(), localDateTime);
            long soPhutChenhLech = ChronoUnit.MINUTES.between(baidang.getNgayBaiDang(), localDateTime);
            //lấy số phút
            if (soThangChenhLech == 0 && soNgayChenhLech == 0 && soGioChenhLech == 0 && soPhutChenhLech != 0) {
                BaiDangDTO baiDangDTO = new BaiDangDTO(soPhutChenhLech + " phút trước", baidang.getTenNguoiDung(), baidang.getHinhAnh(), baidang.getNoiDungBaiDang(),baidang.getHinhAnhNguoiDung());
                baiDangDTOS.add(baiDangDTO);
                //lấy số giờ thoiGianBatDauDangBai, String tenNguoiDung, String hinhAnh, String noiDungBaiDang
            } else if (soThangChenhLech == 0 && soNgayChenhLech == 0 && soGioChenhLech != 0) {
                BaiDangDTO baiDangDTO = new BaiDangDTO(soGioChenhLech + " giờ trước", baidang.getTenNguoiDung(), baidang.getHinhAnh(), baidang.getNoiDungBaiDang() ,baidang.getHinhAnhNguoiDung());
                baiDangDTOS.add(baiDangDTO);
                //lấy ngày
            } else if (soThangChenhLech == 0 && soNgayChenhLech != 0) {
                BaiDangDTO baiDangDTO = new BaiDangDTO(soNgayChenhLech + " ngày trước", baidang.getTenNguoiDung(), baidang.getHinhAnh(), baidang.getNoiDungBaiDang() ,baidang.getHinhAnhNguoiDung());
                baiDangDTOS.add(baiDangDTO);
                //lấy tháng
            } else {
                BaiDangDTO baiDangDTO = new BaiDangDTO(soThangChenhLech + " tháng trước", baidang.getTenNguoiDung(), baidang.getHinhAnh(), baidang.getNoiDungBaiDang() ,baidang.getHinhAnhNguoiDung());
                baiDangDTOS.add(baiDangDTO);
            }
        }
        return baiDangDTOS;
    }

}
