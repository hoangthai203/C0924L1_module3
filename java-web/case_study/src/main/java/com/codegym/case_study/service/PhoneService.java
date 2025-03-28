package com.codegym.case_study.service;

import com.codegym.case_study.model.Phone;
import com.codegym.case_study.repository.PhoneRepository;
import java.util.List;

public class PhoneService implements IPhoneService {
    private PhoneRepository phoneRepository = new PhoneRepository();

    @Override
    public List<Phone> findAll() {
        return phoneRepository.getAllPhones();
    }

    @Override
    public Phone findById(int id) { // Cài đặt phương thức tìm theo ID
        return phoneRepository.getPhoneById(id);
    }
}
