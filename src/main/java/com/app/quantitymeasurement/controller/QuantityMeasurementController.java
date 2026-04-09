package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.QuantityRequestDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quantity")
public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(
            IQuantityMeasurementService service) {
        this.service = service;
    }

    @PostMapping("/compare")
    public boolean compare(@RequestBody QuantityRequestDTO dto) {
        return service.compare(dto);
    }

    @PostMapping("/add")
    public double add(@RequestBody QuantityRequestDTO dto) {
        return service.add(dto);
    }

    @PostMapping("/subtract")
    public double subtract(@RequestBody QuantityRequestDTO dto) {
        return service.subtract(dto);
    }

    @PostMapping("/divide")
    public double divide(@RequestBody QuantityRequestDTO dto) {
        return service.divide(dto);
    }
}