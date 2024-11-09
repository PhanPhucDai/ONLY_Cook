package com.example.cook.Cook.Service;

import com.example.cook.Cook.DTO.BaiDangDTO;
import com.example.cook.Cook.Dao.BaiDangDao;
import com.example.cook.Cook.Entity.BaiDang;
import com.example.cook.Cook.Entity.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BaiDangService {
    @Autowired
    private BaiDangDao baiDangDao;
    @Transactional
    public List<BaiDangDTO> baiDangDTOList(){
        List<BaiDangDTO> list=baiDangDao.AllBaiDang();

        return list;
    }

    @Transactional
    public List<BaiDangDTO > baiDangbyMaNguoiDung(NguoiDung maNguoiDung) {
        List<BaiDangDTO> list= baiDangDao.findAllByMaNguoiDung(maNguoiDung.getMaNguoiDung());
        return list;
    }
    @Transactional
    public void luuBaiDang(BaiDang baiDang) {
        BaiDang baiDang1=kiemTraThoiGian(baiDang);
        baiDangDao.save(baiDang1);
    }



    public BaiDang kiemTraThoiGian(BaiDang baiDang) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        currentDateTime.format(dateTimeFormatter);
        baiDang.setNgayBaiDang(currentDateTime);
        return baiDang;
    }





}
