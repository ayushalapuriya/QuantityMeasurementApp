package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeightMeasurementTest {

    private static final double EPS = 1e-5;

    // -------- Equality --------

    @Test
    void kilogramEqualsGram() {
        assertTrue(
            new QuantityWeight(1, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1000, WeightUnit.GRAM))
        );
    }

    @Test
    void kilogramEqualsPound() {
        assertTrue(
            new QuantityWeight(1, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(2.20462, WeightUnit.POUND))
        );
    }

    @Test
    void inequalityCheck() {
        assertFalse(
            new QuantityWeight(1, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(2, WeightUnit.KILOGRAM))
        );
    }

    // -------- Conversion --------

    @Test
    void convertKgToGram() {

        QuantityWeight result =
                new QuantityWeight(1, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(1000, result.getValue(), EPS);
    }

    @Test
    void convertPoundToKg() {

        QuantityWeight result =
                new QuantityWeight(2.20462, WeightUnit.POUND)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 0.01);
    }

    // -------- Addition --------

    @Test
    void addSameUnit() {

        QuantityWeight result =
                new QuantityWeight(1, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(2, WeightUnit.KILOGRAM));

        assertEquals(3, result.getValue(), EPS);
    }

    @Test
    void addCrossUnit() {

        QuantityWeight result =
                new QuantityWeight(1, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(1000, WeightUnit.GRAM));

        assertEquals(2, result.getValue(), EPS);
    }

    @Test
    void addWithTargetUnit() {

        QuantityWeight result =
                QuantityWeight.add(
                        new QuantityWeight(1, WeightUnit.KILOGRAM),
                        new QuantityWeight(1000, WeightUnit.GRAM),
                        WeightUnit.GRAM);

        assertEquals(2000, result.getValue(), EPS);
    }

    // -------- Category Safety --------

    @Test
    void weightNotEqualToLength() {

        QuantityWeight weight =
                new QuantityWeight(1, WeightUnit.KILOGRAM);

        Quantity length =
                new Quantity(1, LengthUnit.FEET);

        assertFalse(weight.equals(length));
    }
}