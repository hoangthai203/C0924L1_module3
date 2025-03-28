package com.codegym.case_study.service;

import com.codegym.case_study.model.DichVu;

import java.sql.SQLException;
import java.util.List;

public interface IDichVuService {
    List<DichVu> layTatCaDichVu() throws SQLException;

    DichVu timDichVuTheoId(int id) throws SQLException;

    void themDichVu(DichVu dichVu) throws SQLException;

    void capNhatDichVu(DichVu dichVu) throws SQLException;

    void xoaDichVu(int id) throws SQLException;

    void capNhatTrangThaiDichVu(int id, String trangThai) throws SQLException;
}
