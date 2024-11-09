package com.example.cook.Cook.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Cấu hình để phục vụ tệp hình ảnh từ thư mục trên ổ đĩa
        registry.addResourceHandler("/img_NguoiDung/**")
                .addResourceLocations("file:D:/img_NguoiDung/");
    }
}
