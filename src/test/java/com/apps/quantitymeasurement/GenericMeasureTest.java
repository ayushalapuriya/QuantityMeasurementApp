package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GenericMeasureTest {

    // ---------- LENGTH TESTS ----------

    @Test
    void shouldReturnTrue_whenOneFeetEqualsTwelveInches() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    void shouldConvertFeetToInches() {

        Quantity<LengthUnit> q =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                q.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue());
    }

    @Test
    void shouldAddFeetAndInches() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                q1.add(q2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue());
    }

    // ---------- WEIGHT TESTS ----------

    @Test
    void shouldReturnTrue_whenOneKgEqualsThousandGram() {

        Quantity<WeightUnit> w1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void shouldConvertKgToGram() {

        Quantity<WeightUnit> w =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                w.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue());
    }

    @Test
    void shouldAddKgAndGram() {

        Quantity<WeightUnit> w1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                w1.add(w2, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue());
    }

    // ---------- CROSS CATEGORY TEST ----------

    @Test
    void shouldReturnFalse_whenComparingLengthAndWeight() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // ---------- CONSTRUCTOR VALIDATION ----------

    @Test
    void shouldThrowException_whenUnitIsNull() {

        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(1.0, null));
    }

    @Test
    void shouldThrowException_whenValueIsNaN() {

        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(Double.NaN, LengthUnit.FEET));
    }
}