package com.example.cook.Cook.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.sql.DataSource;
@Configuration
public class Security   {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/img_congThuc/**", "/img_baidang/**");
    }

    @Bean
    @Autowired
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager userDetailsManager=new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select ten_dang_nhap, mat_Khau,enable  from nguoi_dung where ten_dang_nhap=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select ten_dang_nhap, vai_tro from nguoi_dung where ten_dang_nhap=?");
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated() // Tất cả các request đều yêu cầu xác thực

                )
                .formLogin(form -> form
                        .loginPage("/form-login")
                        .loginProcessingUrl("/kiemtradangnhap")
                        .usernameParameter("taikhoan")
                        .passwordParameter("matkhau")
                        .defaultSuccessUrl("/kiem_tra_dang_nhap", true)
                        .permitAll() )

                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());
        return httpSecurity.build();
    }



}
