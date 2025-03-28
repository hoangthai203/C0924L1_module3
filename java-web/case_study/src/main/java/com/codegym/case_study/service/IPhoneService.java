package com.codegym.case_study.service;

import com.codegym.case_study.model.Phone;
import java.util.List;

public interface IPhoneService {
    List<Phone> findAll(); // Lấy danh sách tất cả sản phẩm
    Phone findById(int id); // Thêm phương thức tìm sản phẩm theo ID
}
