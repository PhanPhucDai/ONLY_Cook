package com.example.cook.Cook.security;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
  import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
@Configuration
public class Security   {



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
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("img.png" ,"/tao-tai-khoan").permitAll()
                                .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/form-login")
                        .loginProcessingUrl("/kiemtradangnhap")
                        .usernameParameter("taikhoan")
                        .passwordParameter("matkhau")
                        .defaultSuccessUrl("/kiem_tra_dang_nhap", true)
                        .permitAll())
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());
        return httpSecurity.build();
    }



}
