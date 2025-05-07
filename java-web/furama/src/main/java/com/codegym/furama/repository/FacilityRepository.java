package com.codegym.furama.repository;

import com.codegym.furama.model.Facility;

import java.util.ArrayList;
import java.util.List;

public class FacilityRepository implements IFacilityRepository {
    private static final List<Facility> facilityList = new ArrayList<>();

    static {
        facilityList.add(new Facility(1, "Villa Deluxe", 120.0, 2000000, 6, "Ngày", "Villa"));
        facilityList.add(new Facility(2, "Phòng Hạng A", 35.0, 500000, 2, "Giờ", "Room"));
    }

    @Override
    public List<Facility> findAll() {
        return facilityList;
    }

    @Override
    public void save(Facility facility) {
        facilityList.add(facility);
    }

    @Override
    public Facility findById(int id) {
        for (Facility facility : facilityList) {
            if (facility.getId() == id) return facility;
        }
        return null;
    }

    @Override
    public void update(Facility facility) {
        Facility existing = findById(facility.getId());
        if (existing != null) {
            existing.setName(facility.getName());
            existing.setArea(facility.getArea());
            existing.setCost(facility.getCost());
            existing.setMaxPeople(facility.getMaxPeople());
            existing.setRentType(facility.getRentType());
            existing.setFacilityType(facility.getFacilityType());
        }
    }

    @Override
    public void delete(int id) {
        facilityList.removeIf(facility -> facility.getId() == id);
    }
}