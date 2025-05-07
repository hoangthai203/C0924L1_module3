package com.codegym.furama.service;

import com.codegym.furama.model.Facility;
import com.codegym.furama.repository.FacilityRepository;
import com.codegym.furama.repository.IFacilityRepository;

import java.util.List;

public class FacilityService implements IFacilityService {
    private final IFacilityRepository facilityRepository = new FacilityRepository();

    @Override
    public List<Facility> findAll() {
        return facilityRepository.findAll();
    }

    @Override
    public void save(Facility facility) {
        facilityRepository.save(facility);
    }

    @Override
    public Facility findById(int id) {
        return facilityRepository.findById(id);
    }

    @Override
    public void update(Facility facility) {
        facilityRepository.update(facility);
    }

    @Override
    public void delete(int id) {
        facilityRepository.delete(id);
    }
}