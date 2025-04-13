package com.codegym.case_study.service;

import com.codegym.case_study.model.User;
import com.codegym.case_study.repository.UserRepository;

public class LoginService {
    private UserRepository repository = new UserRepository();

    public User kiemTraDangNhap(String tenDangNhap, String matKhau) {
        User nguoiDung = repository.timTheoTenDangNhap(tenDangNhap);
        if (nguoiDung != null && nguoiDung.getMatKhau().equals(matKhau)) {
            return nguoiDung;
        }
        return null;
    }
}
