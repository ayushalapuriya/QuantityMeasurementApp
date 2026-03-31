package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityRequestDTO;

public interface IQuantityMeasurementService {

    boolean compare(QuantityRequestDTO dto);

    double add(QuantityRequestDTO dto);

    double subtract(QuantityRequestDTO dto);

    double divide(QuantityRequestDTO dto);
}