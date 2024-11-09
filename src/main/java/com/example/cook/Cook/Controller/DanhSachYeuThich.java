package com.example.cook.Cook.Controller;

import com.example.cook.Cook.Entity.NguoiDung;
import com.example.cook.Cook.Service.DanhSachYeuThichService;
import com.example.cook.Cook.Service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class DanhSachYeuThich {
    @Autowired
    private DanhSachYeuThichService danhSachYeuThichService;
    @Autowired
    private NguoiDungService nguoiDungService;
    @GetMapping(value = "/Danh-Sach-Yeu-Thich")
    public String getDanhSachYeuThich(Model model){
        System.out.println(danhSachYeuThichService.getDanhSachYeuThich());
        List<com.example.cook.Cook.Entity.DanhSachYeuThich> danhSachYeuThichList= danhSachYeuThichService.getDanhSachYeuThich();
        model.addAttribute("dsYeuThich",danhSachYeuThichList);
        return "DanhSachYeuThich";
    }
    @PostMapping(value = "/them-cong-thuc")
    public String addCongThuc(@RequestParam("idDanhSach") String maDanhSach, @RequestParam("maCongThuc") String maCongthuc, Model model , RedirectAttributes redirectAttributes){
        int result=danhSachYeuThichService.themYeuThich(Integer.parseInt(maDanhSach),Integer.parseInt(maCongthuc));
        if(result== -1 ){
            redirectAttributes.addFlashAttribute("errol","Bài viết này đã được lưu trước đó");
            return  "redirect:/Cong-thuc";
        }else if(result== 0 ){
            redirectAttributes.addFlashAttribute("errol","Lưu thất bại");
            return  "redirect:/Cong-thuc";}
        redirectAttributes.addFlashAttribute("success","Lưu thành công ");
        return "redirect:/Cong-thuc";
    }

    @PostMapping(value = "/xoa-danh-sach")
    public String xoaCongThuc(@RequestParam("maDanhSach") String maDanhSach, Model model){
        danhSachYeuThichService.xoaDanhSachYeuThich(Integer.parseInt(maDanhSach));
        return "redirect:/Danh-Sach-Yeu-Thich";
    }
    @PostMapping(value = "/tao-danh-sach")
    public String addDanhSach(@RequestParam(value = "tenDanhSach")String tenDanhSach, Model model){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = localDateTime.format(dateTimeFormatter);
        LocalDate localDate = LocalDate.parse(formattedDate, dateTimeFormatter);
        Date date = Date.valueOf(localDate);
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        NguoiDung nguoiDung=nguoiDungService.getNguoiDung(authentication.getName());
        com.example.cook.Cook.Entity.DanhSachYeuThich danhSachYeuThich=new com.example.cook.Cook.Entity.DanhSachYeuThich(date,tenDanhSach,nguoiDung);
        danhSachYeuThichService.themYeuThich(danhSachYeuThich);
        return "redirect:/Danh-Sach-Yeu-Thich";
    }

        @GetMapping("/Chi-tiet-danh-sach-yeu-thich/{maDanhSach}")
        public String getCongThucByIdDanhSach(@PathVariable String maDanhSach, Model model) {
        com.example.cook.Cook.Entity.DanhSachYeuThich danhSachYeuThich=danhSachYeuThichService.layTenDanhSachCongThuc(Integer.parseInt(maDanhSach));
            model.addAttribute("tenDanhSach",danhSachYeuThich.getTenDanhSachYeuThich());
            model.addAttribute("listCongThuc", danhSachYeuThichService.layDanhSachCongThuc(Integer.parseInt(maDanhSach)));
            return "Chi_tiet_danh_sach_cong_thuc";
        }



}
