package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

public class QuantityService {

    private final IQuantityMeasurementRepository repository =
            new QuantityMeasurementCacheRepository();

    public <U extends IMeasurable> boolean isEqual(
            Quantity<U> q1,
            Quantity<U> q2) {

        boolean result = q1.equals(q2);

        repository.save(
                new QuantityMeasurementEntity(
                        "EQUAL",
                        q1.getValue(),
                        q2.getValue(),
                        result ? 1 : 0
                )
        );

        return result;
    }

    public <U extends IMeasurable> Quantity<U> convert(
            Quantity<U> quantity,
            U targetUnit) {

        Quantity<U> result = quantity.convertTo(targetUnit);

        repository.save(
                new QuantityMeasurementEntity(
                        "CONVERT",
                        quantity.getValue(),
                        0,
                        result.getValue()
                )
        );

        return result;
    }

    public <U extends IMeasurable> Quantity<U> add(
            Quantity<U> q1,
            Quantity<U> q2,
            U unit) {

        Quantity<U> result = q1.add(q2, unit);

        repository.save(
                new QuantityMeasurementEntity(
                        "ADD",
                        q1.getValue(),
                        q2.getValue(),
                        result.getValue()
                )
        );

        return result;
    }

    public <U extends IMeasurable> Quantity<U> subtract(
            Quantity<U> q1,
            Quantity<U> q2,
            U unit) {

        Quantity<U> result = q1.subtract(q2, unit);

        repository.save(
                new QuantityMeasurementEntity(
                        "SUBTRACT",
                        q1.getValue(),
                        q2.getValue(),
                        result.getValue()
                )
        );

        return result;
    }

    public <U extends IMeasurable> double divide(
            Quantity<U> q1,
            Quantity<U> q2) {

        double result = q1.divide(q2);

        repository.save(
                new QuantityMeasurementEntity(
                        "DIVIDE",
                        q1.getValue(),
                        q2.getValue(),
                        result
                )
        );

        return result;
    }
}