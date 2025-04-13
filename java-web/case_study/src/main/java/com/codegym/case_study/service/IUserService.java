package com.codegym.case_study.service;

import com.codegym.case_study.model.User;

public interface IUserService {
    User validateLogin(String tenDangNhap, String matKhau);
}
