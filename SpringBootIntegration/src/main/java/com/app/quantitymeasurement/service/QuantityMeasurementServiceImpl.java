package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityRequestDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.Quantity;
import org.springframework.stereotype.Service;

@Service
public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repo;

    public QuantityMeasurementServiceImpl(
            IQuantityMeasurementRepository repo) {
        this.repo = repo;
    }

    private LengthUnit parseLength(String unit) {
        return LengthUnit.valueOf(unit.toUpperCase());
    }

    // ✅ COMPARE RETURNS BOOLEAN
    @Override
    public boolean compare(QuantityRequestDTO dto) {

        Quantity<LengthUnit> q1 =
                new Quantity<>(dto.value1, parseLength(dto.unit1));

        Quantity<LengthUnit> q2 =
                new Quantity<>(dto.value2, parseLength(dto.unit2));

        return q1.equals(q2);
    }

    // ✅ ADD SAVES ENTITY + RETURNS RESULT VALUE
    @Override
    public double add(QuantityRequestDTO dto) {

        Quantity<LengthUnit> q1 =
                new Quantity<>(dto.value1, parseLength(dto.unit1));

        Quantity<LengthUnit> q2 =
                new Quantity<>(dto.value2, parseLength(dto.unit2));

        LengthUnit target = parseLength(dto.targetUnit);

        Quantity<LengthUnit> result = q1.add(q2, target);

        repo.save(new QuantityMeasurementEntity(
                dto.value1,
                dto.unit1,
                dto.value2,
                dto.unit2,
                "ADD",
                result.getValue()
        ));

        return result.getValue();
    }

    // ✅ SUBTRACT
    @Override
    public double subtract(QuantityRequestDTO dto) {

        Quantity<LengthUnit> q1 =
                new Quantity<>(dto.value1, parseLength(dto.unit1));

        Quantity<LengthUnit> q2 =
                new Quantity<>(dto.value2, parseLength(dto.unit2));

        LengthUnit target = parseLength(dto.targetUnit);

        return q1.subtract(q2, target).getValue();
    }

    // ✅ DIVIDE
    @Override
    public double divide(QuantityRequestDTO dto) {

        Quantity<LengthUnit> q1 =
                new Quantity<>(dto.value1, parseLength(dto.unit1));

        Quantity<LengthUnit> q2 =
                new Quantity<>(dto.value2, parseLength(dto.unit2));

        return q1.divide(q2);
    }
}