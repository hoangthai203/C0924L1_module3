package com.codegym.case_study.service;

import com.codegym.case_study.model.DichVu;
import com.codegym.case_study.repository.DichVuRepository;

import java.sql.SQLException;
import java.util.List;

public class DichVuService implements IDichVuService {
    private final DichVuRepository dichVuRepository = new DichVuRepository();

    @Override
    public List<DichVu> layTatCaDichVu() throws SQLException {
        return dichVuRepository.layDanhSachDichVu();
    }

    @Override
    public DichVu timDichVuTheoId(int id) throws SQLException {
        return dichVuRepository.timDichVuTheoId(id);
    }

    @Override
    public void themDichVu(DichVu dichVu) throws SQLException {
        dichVuRepository.themDichVu(dichVu);
    }

    @Override
    public void capNhatDichVu(DichVu dichVu) throws SQLException {
        dichVuRepository.capNhatDichVu(dichVu);
    }

    @Override
    public void xoaDichVu(int id) throws SQLException {
        dichVuRepository.xoaDichVu(id);
    }

    @Override
    public void capNhatTrangThaiDichVu(int id, String trangThai) throws SQLException {
        dichVuRepository.capNhatTrangThaiDichVu(id, trangThai);
    }
}
