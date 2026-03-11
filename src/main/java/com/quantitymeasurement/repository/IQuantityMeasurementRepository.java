package com.quantitymeasurement.repository;

import com.quantitymeasurement.model.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> getAllMeasurements();

    public static void main(String[] args) {
        System.out.println("Testing IQuantityMeasurementRepository interface");
    }
}