package com.codegym.case_study.service;

import com.codegym.case_study.model.Phone;
import java.util.List;

public interface IPhoneService {
    List<Phone> findAll();
    Phone findById(int id);
    List<Phone> timKiemDienThoai(String keyword); // 🆕 Thêm phương thức tìm kiếm
}
