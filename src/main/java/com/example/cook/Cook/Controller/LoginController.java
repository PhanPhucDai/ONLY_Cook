package com.example.cook.Cook.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping(value = "/kiem_tra_dang_nhap")
    public String kiemTraDangNhap(){
        return "redirect:/Trang-chu";
    }

    @GetMapping(value = "/form-login")
    public String trangDangNhap(){
        return "dangNhap_dangKi";
    }

}
