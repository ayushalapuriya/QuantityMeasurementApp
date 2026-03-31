package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {
}