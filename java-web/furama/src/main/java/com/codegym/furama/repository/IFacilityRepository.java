package com.codegym.furama.repository;

import com.codegym.furama.model.Facility;
import java.util.List;

public interface IFacilityRepository {
    List<Facility> findAll();
    void save(Facility facility);
    Facility findById(int id);
    void update(Facility facility);
    void delete(int id);
}
