package com.example.cook.Cook.Controller;

import com.example.cook.Cook.DTO.BaiDangDTO;
import com.example.cook.Cook.Entity.LoaiMonAn;
import com.example.cook.Cook.Entity.NguoiDung;
import com.example.cook.Cook.Service.BaiDangService;
import com.example.cook.Cook.Service.LoaiMonAnService;
import com.example.cook.Cook.Service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import java.nio.file.Paths;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NguoiDungController {
    private static final String path="/img_NguoiDung";
    @Autowired
    private NguoiDungService nguoiDungService;
    @Autowired
    private BaiDangService baiDangService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private LoaiMonAnService loaiMonAnService;
      int soNgauNhien;
      String matKhauMoi;
    @GetMapping(value ="/ban_tin_ca_nhan")
    public String banTinCaNhan(Model model){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String tenDangNhap=authentication.getName();
        NguoiDung nguoiDung=nguoiDungService.getNguoiDung(tenDangNhap);
        List<BaiDangDTO>baiDangDTOS= listTime(baiDangService.baiDangbyMaNguoiDung(nguoiDung));
        List<LoaiMonAn> loaiMonAnList =loaiMonAnService.loaiMonAnlist();
        model.addAttribute("loaiMonAnList",loaiMonAnList );
        model.addAttribute("listLoaiMonAn", loaiMonAnService.loaiMonAnlist());
        model.addAttribute("baiDangs", baiDangDTOS);
        model.addAttribute("nguoiDung", nguoiDung.getTenNguoiDung());
        return "Bantincanhan";
    }

    @GetMapping(value = "/nguoi_dung_view")
    public String getNguoiDung(Model model){
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       String tenDangNhap=authentication.getName();

       System.out.println("Ten dang nhap"+ tenDangNhap);

       NguoiDung nguoiDung=nguoiDungService.getNguoiDung(tenDangNhap);

        System.out.println("Nguoi dung"+ nguoiDung.getTenNguoiDung());

        List<LoaiMonAn> loaiMonAnList =loaiMonAnService.loaiMonAnlist();
       model.addAttribute("loaiMonAnList",loaiMonAnList );
       model.addAttribute("nguoiDung", nguoiDung.getTenNguoiDung());
       model.addAttribute("nguoidung",nguoiDung);
       return "nguoidung";
    }



    @PostMapping("/update_tendangnhap")
    public String updateTenDangNhap(@RequestParam("tenNguoiDung") String tentaikhoan, Model model){
            NguoiDung nguoiDung=nguoiDungService.update_tenNguoiDUng(tentaikhoan);
         return "redirect:/nguoi_dung_view";
    }

    @PostMapping("/update_diaChi")
    public String updateDiaChi(@RequestParam("diaChi") String diaChi, Model model){
        NguoiDung nguoiDung=nguoiDungService.update_diaChi(diaChi);
        return "redirect:/nguoi_dung_view";
    }

    @PostMapping(value="/update_hinhAnh")
    public String updateHinhAnh(@RequestParam("hinhAnh") MultipartFile hinhAnh, Model model) throws IOException {
        if(hinhAnh.isEmpty()){
            model.addAttribute("errol" , "Vui lòng chọn ảnh để thêm");
        }
        try{
            Path path1= Paths.get(path);
            if(!Files.exists(path1)){
                Files.createDirectories(path1);
            }
            String fileName =hinhAnh.getOriginalFilename();
            Path filePath=path1.resolve(fileName);
            Files.write(filePath, hinhAnh.getBytes());
            nguoiDungService.update_hinhAnh(filePath.toString() );
            return "redirect:/nguoi_dung_view";
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/nguoi_dung_view";
    }

    @PostMapping(value="/kiem-tra-doi-mat-khau")
    public String kiemTraDoiMatKhau(@RequestParam("matKhauCu")String matKhauCu,@RequestParam("matKhauMoi")String matKhauMoi, RedirectAttributes redirectAttributes, Model model
    ) throws IOException {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String tenDangNhap=authentication.getName();
        NguoiDung nguoiDung=nguoiDungService.getNguoiDung(tenDangNhap);
        this.matKhauMoi=matKhauMoi;
        int demLoi=0;
        System.out.println(matKhauCu);
        System.out.println(nguoiDung.getMatKhau().substring(6));
        if(!matKhauCu.equals(nguoiDung.getMatKhau().substring(6))) {
            demLoi++;
            redirectAttributes.addFlashAttribute("errol", "Mật khẩu không đúng vui lòng thử lại \n Bạn chỉ được phép nhập 5 lần \n Số lần hiện tại  " + demLoi);
            return "redirect:/nguoi_dung_view";

        }
        if (demLoi > 5) {
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("mm:ss");
            LocalDateTime deadlie = localDateTime.plusMinutes(5);
            String dateTime = dateFormat.format(deadlie);
            redirectAttributes.addFlashAttribute("errol", "Số lần vượt quá mức cho phép hãy thử lại sau: " + dateTime);
            return "redirect:/nguoi_dung_view";
        }
            soNgauNhien = sendEmail(nguoiDung.getDiaChiEmail());
            System.out.println(soNgauNhien);
            redirectAttributes.addFlashAttribute("showModal", true);

        return "redirect:/nguoi_dung_view";
    }

    @PostMapping(value="/doi-mat-khau")
    public String doiMatKhau(@RequestParam("maXacThuc")String maXacThuc, RedirectAttributes redirectAttributes) throws IOException {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String tenDangNhap=authentication.getName();
        NguoiDung nguoiDung=nguoiDungService.getNguoiDung(tenDangNhap);
        System.out.println(maXacThuc);
        System.out.println(soNgauNhien);
        if (Integer.parseInt(maXacThuc)!=soNgauNhien) {
            redirectAttributes.addFlashAttribute("result", false);
            return "redirect:/nguoi_dung_view";
        }


        nguoiDungService.update_matKhau("{noop}"+matKhauMoi);
        redirectAttributes.addFlashAttribute("result",true);
        return "redirect:/nguoi_dung_view";
    }

    public List listTime(List<BaiDangDTO> danhSachBaiDang){
        LocalDateTime localDateTime=LocalDateTime.now();
        List<BaiDangDTO> baiDangDTOS = new ArrayList<>();
        for(BaiDangDTO baidang: danhSachBaiDang) {
            long soNgayChenhLech = ChronoUnit.DAYS.between(baidang.getNgayBaiDang(), localDateTime);
            long soGioChenhLech = ChronoUnit.HOURS.between(baidang.getNgayBaiDang(), localDateTime);
            long soThangChenhLech = ChronoUnit.MONTHS.between(baidang.getNgayBaiDang(), localDateTime);
            long soPhutChenhLech = ChronoUnit.MINUTES.between(baidang.getNgayBaiDang(), localDateTime);
            //lấy số phút
            if (soThangChenhLech == 0 && soNgayChenhLech == 0 && soGioChenhLech == 0 && soPhutChenhLech != 0) {
                BaiDangDTO baiDangDTO = new BaiDangDTO(soPhutChenhLech + " phút trước", baidang.getTenNguoiDung(), baidang.getHinhAnh(), baidang.getNoiDungBaiDang(),baidang.getHinhAnhNguoiDung());
                baiDangDTOS.add(baiDangDTO);
                //lấy số giờ
            } else if (soThangChenhLech == 0 && soNgayChenhLech == 0 && soGioChenhLech != 0) {
                BaiDangDTO baiDangDTO = new BaiDangDTO(soGioChenhLech + " giờ trước", baidang.getTenNguoiDung(), baidang.getHinhAnh(), baidang.getNoiDungBaiDang(),baidang.getHinhAnhNguoiDung());
                baiDangDTOS.add(baiDangDTO);
                //lấy ngày
            } else if (soThangChenhLech == 0 && soNgayChenhLech != 0) {
                BaiDangDTO baiDangDTO = new BaiDangDTO(soNgayChenhLech + " ngày trước", baidang.getTenNguoiDung(), baidang.getHinhAnh(), baidang.getNoiDungBaiDang(),baidang.getHinhAnhNguoiDung());
                baiDangDTOS.add(baiDangDTO);
                //lấy tháng
            } else {
                BaiDangDTO baiDangDTO = new BaiDangDTO(soThangChenhLech + " tháng trước", baidang.getTenNguoiDung(), baidang.getHinhAnh(), baidang.getNoiDungBaiDang(),baidang.getHinhAnhNguoiDung());
                baiDangDTOS.add(baiDangDTO);
            }
        }
        return baiDangDTOS;
    }


    public int sendEmail(String to){
        int ranDom = (int)(10000 + Math.random() * 90000);
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject("Mã xác thực");
        mailMessage.setText("Vui lòng nhập mã này"+ ranDom);
        javaMailSender.send(mailMessage);
        return ranDom;
    }
}
