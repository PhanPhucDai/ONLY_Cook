package com.example.cook.Cook.Controller;

import com.example.cook.Cook.DTO.BaiDangDTO;
import com.example.cook.Cook.Dao.CongThucDao;
import com.example.cook.Cook.Entity.CongThuc;
import com.example.cook.Cook.Entity.LoaiMonAn;
import com.example.cook.Cook.Entity.NguoiDung;
import com.example.cook.Cook.Service.CongThucService;
import com.example.cook.Cook.Service.DanhSachYeuThichService;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Controller
public class Cong_thuc {
    @Autowired
    private LoaiMonAnService loaiMonAnService;
    @Autowired
    private CongThucService congThucService;
    @Autowired
    private NguoiDungService nguoiDungService;
    @Autowired
    private CongThucDao congThucDao;
    @Autowired
    private DanhSachYeuThichService danhSachYeuThichService;
    @GetMapping("Cong-thuc")
    public String getAllCongThuc(@RequestParam(value = "errol", required = false) String errol,
                                 @RequestParam(value = "success", required = false) String success,
                                 Model model) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String tenDangNhap=authentication.getName();
        NguoiDung nguoiDung=nguoiDungService.getNguoiDung(tenDangNhap);
        model.addAttribute("nguoiDung", nguoiDung.getTenNguoiDung());
        model.addAttribute("dsYeuThich", danhSachYeuThichService.getDanhSachYeuThich());
        model.addAttribute("listCongThuc", congThucDao.getcongThucList());
        List<LoaiMonAn> loaiMonAnList =loaiMonAnService.loaiMonAnlist();
        model.addAttribute("loaiMonAnList",loaiMonAnList );
         if (errol != null) {
            model.addAttribute("errol", errol);
        }
        if (success != null) {
            model.addAttribute("success", success);
        }
        return "Cong-thuc";
    }

    @GetMapping("tim-cong-thuc")
    public String getCongThucByInput(
                                 Model model,
                                 @RequestParam(value = "timCongThuc") String timCongThuc) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String tenDangNhap=authentication.getName();
        NguoiDung nguoiDung=nguoiDungService.getNguoiDung(tenDangNhap);
        List<LoaiMonAn> loaiMonAnList =loaiMonAnService.loaiMonAnlist();
        model.addAttribute("loaiMonAnList",loaiMonAnList );
        model.addAttribute("nguoiDung", nguoiDung.getTenNguoiDung());
        model.addAttribute("dsYeuThich", danhSachYeuThichService.getDanhSachYeuThich());
        model.addAttribute("listCongThuc", congThucDao.getCongThucByInput("%"+timCongThuc+"%"));

        return "Cong-thuc";
    }


    @GetMapping("chi-tiet-cong-thuc")
    public String getCongThucById(@RequestParam("maCongThuc")int maCongThuc, Model model){
        List<LoaiMonAn> loaiMonAnList =loaiMonAnService.loaiMonAnlist();
        model.addAttribute("loaiMonAnList",loaiMonAnList );
        model.addAttribute("dsYeuThich", danhSachYeuThichService.getDanhSachYeuThich());
        model.addAttribute("congThuc",congThucDao.getCongThucById(maCongThuc));
        return "Chi-tiet-cong-thuc";
    }

    @PostMapping("luu-cong-thuc")
    public String saveCongThuc(@RequestParam("tenCongThuc") String tenCongThuc,
                               @RequestParam("moTa") String moTa,
                               @RequestParam("nguyenLieu") String nguyenLieu,
                               @RequestParam("cacBuocThucHien") String cacBuocThucHien,
                               @RequestParam("loaiCongThuc") String loaiCongThuc,
                               @RequestParam("hinhAnh") MultipartFile hinhAnh,
                               @RequestParam("diemDanhGiaTrungBinh") String diemDanhGiaTrungBinh,
                               @RequestParam("videoURL") String videoURL,
                               Model model, RedirectAttributes redirect) {

        if (tenCongThuc.isEmpty() || moTa.isEmpty() || nguyenLieu.isEmpty() || cacBuocThucHien.isEmpty()
                || diemDanhGiaTrungBinh.isEmpty() || videoURL.isEmpty() || loaiCongThuc.isEmpty()) {
            redirect.addAttribute("errol", "Vui lòng nhập đầy đủ thông tin");
            return "redirect:/Trang-chu";
        }

        String resultHinhAnh = createPath(hinhAnh);
        if (resultHinhAnh.equalsIgnoreCase("errol")) {
            redirect.addAttribute("errol", "File ảnh không đúng định dạng vui lòng thử lại");
            return "redirect:/Trang-chu";
        }

        CongThuc congThuc = new CongThuc( tenCongThuc,  moTa,  resultHinhAnh,  videoURL,  cacBuocThucHien,  diemDanhGiaTrungBinh,  nguyenLieu);
        congThucService.themCongThuc(congThuc,Integer.parseInt(loaiCongThuc));

        redirect.addAttribute("success", "Công thức đã được lưu thành công");
        return "redirect:/Trang-chu";
    }

    public String createPath(MultipartFile hinhAnh)   {
        Path path=Path.of("/img_congThuc");
        Path path1;
        if(!checkFileEndWith(hinhAnh)){
            return "errol";
        }
        try {
            if(!Files.exists(path)){
                Files.createDirectories(path);
            }
            String pathHinhAnh=hinhAnh.getOriginalFilename();
             path1=path.resolve(pathHinhAnh);
            Path files=Files.write(path1,hinhAnh.getBytes());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path1.toString();
    }

    public boolean checkFileEndWith(MultipartFile hinhAnh){
        if(hinhAnh.isEmpty()){
            return false;
        }
        String fileName=hinhAnh.getOriginalFilename();
        if (fileName != null) {
            fileName = fileName.toLowerCase();

             return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") ||
                    fileName.endsWith(".png") || fileName.endsWith(".gif") ||
                    fileName.endsWith(".bmp") || fileName.endsWith(".jfif");
        }

        return false;
    }
}
