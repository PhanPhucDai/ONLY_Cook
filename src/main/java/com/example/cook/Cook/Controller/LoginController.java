package com.example.cook.Cook.Controller;

import com.example.cook.Cook.Entity.NguoiDung;
import com.example.cook.Cook.Service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @Autowired
    private NguoiDungService nguoiDungService;
    @GetMapping(value = "/kiem_tra_dang_nhap")
    public String kiemTraDangNhap(){
        return "redirect:/Trang-chu";
    }


    @GetMapping(value = "/form-login")
    public String trangDangNhap(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "success", required = false) String success,
                                Model model) {
        if (error != null) {
            model.addAttribute("error", "Đăng nhập thất bại. Vui lòng thử lại.");
        }
        if (success != null) {
            model.addAttribute("success", "Đăng ký thành công. Vui lòng đăng nhập.");
        }
        return "dangNhap_dangKi";
    }


    @PostMapping("/tao-tai-khoan")
    public String taoTaiKhoan(@RequestParam("tenDangNhap")String tenDangNhap,
                              @RequestParam("email")String email,
                              @RequestParam("taoMatKhau")String matKhau,
                              @RequestParam("confirmPassword")String xacNhanMatKhau,
                              @RequestParam("hoVaTen")String hoVaTen,
                              RedirectAttributes redirectAttributes){
        if (!(matKhau.equals(xacNhanMatKhau))) {
            redirectAttributes.addFlashAttribute("error", "Xác nhận mật khẩu không chính xác vui lòng thử lại");
            return "redirect:/form-login";
        }
        if(!(nguoiDungService.taoNguoiDung(tenDangNhap, email, matKhau, hoVaTen))){
            redirectAttributes.addFlashAttribute("errol","Tạo tài khoản không thành công");
            return "redirect:/form-login";
        }
        redirectAttributes.addFlashAttribute("success","Tạo tài khoản thành công");
        return "redirect:/form-login";
    }
}
