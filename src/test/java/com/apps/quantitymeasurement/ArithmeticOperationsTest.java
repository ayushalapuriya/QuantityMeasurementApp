package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticOperationsTest {

    // ---------- SUBTRACTION ----------

    @Test
    void shouldSubtractSameUnit() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(5, LengthUnit.FEET);

        assertEquals(5,
                q1.subtract(q2).getValue());
    }

    @Test
    void shouldSubtractCrossUnit() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(6, LengthUnit.INCHES);

        assertEquals(9.5,
                q1.subtract(q2).getValue());
    }

    @Test
    void shouldReturnNegativeResult() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(5, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(10, LengthUnit.FEET);

        assertEquals(-5,
                q1.subtract(q2).getValue());
    }

    // ---------- DIVISION ----------

    @Test
    void shouldDivideSameUnit() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2, LengthUnit.FEET);

        assertEquals(5.0, q1.divide(q2));
    }

    @Test
    void shouldDivideCrossUnit() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(24, LengthUnit.INCHES);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2, LengthUnit.FEET);

        assertEquals(1.0, q1.divide(q2));
    }

    @Test
    void shouldThrowExceptionWhenDivideByZero() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class,
                () -> q1.divide(q2));
    }
}