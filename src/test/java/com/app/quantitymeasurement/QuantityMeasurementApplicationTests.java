package com.app.quantitymeasurement;

import com.app.quantitymeasurement.dto.QuantityRequestDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuantityMeasurementApplicationTests {

    @Autowired
    private IQuantityMeasurementService service;

    // ---------- SPRING CONTEXT TEST ----------
    @Test
    void contextLoads() {
        assertNotNull(service);
    }

    // ---------- COMPARE TEST ----------
    @Test
    void givenSameLength_whenCompare_thenReturnTrue() {

        QuantityRequestDTO dto = new QuantityRequestDTO();
        dto.value1 = 1;
        dto.unit1 = "FEET";
        dto.value2 = 12;
        dto.unit2 = "INCHES";

        boolean result = service.compare(dto);

        assertTrue(result);
    }

    // ---------- ADD TEST ----------
    @Test
    void givenLengths_whenAdd_thenReturnResult() {

        QuantityRequestDTO dto = new QuantityRequestDTO();
        dto.value1 = 1;
        dto.unit1 = "FEET";
        dto.value2 = 12;
        dto.unit2 = "INCHES";
        dto.targetUnit = "FEET";

        double result = service.add(dto);

        assertEquals(2.0, result);
    }

    // ---------- SUBTRACT TEST ----------
    @Test
    void givenLengths_whenSubtract_thenReturnResult() {

        QuantityRequestDTO dto = new QuantityRequestDTO();
        dto.value1 = 2;
        dto.unit1 = "FEET";
        dto.value2 = 12;
        dto.unit2 = "INCHES";
        dto.targetUnit = "FEET";

        double result = service.subtract(dto);

        assertEquals(1.0, result);
    }

    // ---------- DIVIDE TEST ----------
    @Test
    void givenLengths_whenDivide_thenReturnRatio() {

        QuantityRequestDTO dto = new QuantityRequestDTO();
        dto.value1 = 2;
        dto.unit1 = "FEET";
        dto.value2 = 1;
        dto.unit2 = "FEET";

        double result = service.divide(dto);

        assertEquals(2.0, result);
    }
}