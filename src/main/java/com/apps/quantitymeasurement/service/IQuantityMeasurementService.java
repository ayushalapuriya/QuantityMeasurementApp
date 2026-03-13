package com.apps.quantitymeasurement.service;

import java.util.List;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementService {

    void recordAddition(String type, double v1, double v2, double result);

    List<QuantityMeasurementEntity> getAllMeasurements();

    int getTotalMeasurements();

    void clearAll();
}