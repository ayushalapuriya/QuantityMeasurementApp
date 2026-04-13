package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementTest {

    QuantityController controller = new QuantityController();

    @Test
    void testFeetAndInchesEquality() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);

        assertTrue(controller.checkEquality(q1, q2));
    }

    @Test
    void testFeetAndYardsEquality() {
        Quantity<LengthUnit> q1 = new Quantity<>(3, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1, LengthUnit.YARDS);

        assertTrue(controller.checkEquality(q1, q2));
    }

    @Test
    void testWeightEquality() {
        Quantity<WeightUnit> w1 = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000, WeightUnit.GRAM);

        assertTrue(controller.checkEquality(w1, w2));
    }

    @Test
    void testAddition() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result = controller.add(q1, q2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue());
    }

    @Test
    void testSubtraction() {
        Quantity<LengthUnit> q1 = new Quantity<>(2, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result = controller.subtract(q1, q2, LengthUnit.FEET);

        assertEquals(1.0, result.getValue());
    }

    @Test
    void testDivision() {
        Quantity<LengthUnit> q1 = new Quantity<>(2, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1, LengthUnit.FEET);

        double result = controller.divide(q1, q2);

        assertEquals(2.0, result);
    }
}