package com.codegym.case_study.repository;

import com.codegym.case_study.model.DichVu;
import java.sql.SQLException;
import java.util.List;

public interface IDichVuRepository {
    List<DichVu> layDanhSachDichVu() throws SQLException;
    DichVu timDichVuTheoId(int id) throws SQLException;
    void themDichVu(DichVu dichVu) throws SQLException;
    void capNhatDichVu(DichVu dichVu) throws SQLException;
    void xoaDichVu(int id) throws SQLException;
    void capNhatTrangThaiDichVu(int id, String trangThai) throws SQLException;
}
