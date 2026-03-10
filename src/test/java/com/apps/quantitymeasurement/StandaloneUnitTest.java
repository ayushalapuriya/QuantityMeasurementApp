package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StandaloneUnitTest {

    private static final double EPSILON = 0.0001;

    // SAME UNIT ADDITION
    @Test
    void addSameUnitFeet() {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(2.0, LengthUnit.FEET);

        Quantity result = Quantity.add(q1, q2, LengthUnit.FEET);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void addSameUnitInches() {

        Quantity q1 = new Quantity(6.0, LengthUnit.INCHES);
        Quantity q2 = new Quantity(6.0, LengthUnit.INCHES);

        Quantity result = Quantity.add(q1, q2, LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    // CROSS UNIT ADDITION
    @Test
    void addFeetAndInches_TargetFeet() {

        Quantity feet = new Quantity(1.0, LengthUnit.FEET);
        Quantity inches = new Quantity(12.0, LengthUnit.INCHES);

        Quantity result = Quantity.add(feet, inches, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void addInchesAndFeet_TargetInches() {

        Quantity inches = new Quantity(12.0, LengthUnit.INCHES);
        Quantity feet = new Quantity(1.0, LengthUnit.FEET);

        Quantity result = Quantity.add(inches, feet, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
    }

    @Test
    void addYardAndFeet() {

        Quantity yard = new Quantity(1.0, LengthUnit.YARDS);
        Quantity feet = new Quantity(3.0, LengthUnit.FEET);

        Quantity result = Quantity.add(yard, feet, LengthUnit.YARDS);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void addCentimeterAndInch() {

        Quantity cm = new Quantity(2.54, LengthUnit.CENTIMETERS);
        Quantity inch = new Quantity(1.0, LengthUnit.INCHES);

        Quantity result =
                Quantity.add(cm, inch, LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), 0.01);
    }

    // COMMUTATIVITY PROPERTY
    @Test
    void additionShouldBeCommutative() {

        Quantity a = new Quantity(1.0, LengthUnit.FEET);
        Quantity b = new Quantity(12.0, LengthUnit.INCHES);

        Quantity r1 = Quantity.add(a, b, LengthUnit.FEET);
        Quantity r2 = Quantity.add(b, a, LengthUnit.FEET);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }

    // IDENTITY (ADDING ZERO)
    @Test
    void addZeroShouldReturnSameValue() {

        Quantity q1 = new Quantity(5.0, LengthUnit.FEET);
        Quantity zero = new Quantity(0.0, LengthUnit.INCHES);

        Quantity result = Quantity.add(q1, zero, LengthUnit.FEET);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    // NEGATIVE VALUES
    @Test
    void addNegativeValues() {

        Quantity q1 = new Quantity(5.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(-2.0, LengthUnit.FEET);

        Quantity result = Quantity.add(q1, q2, LengthUnit.FEET);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    // LARGE & SMALL VALUES
    @Test
    void addLargeValues() {

        Quantity q1 = new Quantity(1e6, LengthUnit.FEET);
        Quantity q2 = new Quantity(1e6, LengthUnit.FEET);

        Quantity result = Quantity.add(q1, q2, LengthUnit.FEET);

        assertEquals(2e6, result.getValue(), EPSILON);
    }

    @Test
    void addSmallValues() {

        Quantity q1 = new Quantity(0.001, LengthUnit.FEET);
        Quantity q2 = new Quantity(0.002, LengthUnit.FEET);

        Quantity result = Quantity.add(q1, q2, LengthUnit.FEET);

        assertEquals(0.003, result.getValue(), EPSILON);
    }

    // VALIDATION TESTS
    @Test
    void nullSecondOperandShouldThrowException() {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> Quantity.add(q1, null, LengthUnit.FEET)
        );
    }

    @Test
    void nullTargetUnitShouldThrowException() {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(1.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> Quantity.add(q1, q2, null)
        );
    }

    // EQUALITY TESTS
    @Test
    void equalityAcrossUnits() {

        Quantity feet = new Quantity(1.0, LengthUnit.FEET);
        Quantity inches = new Quantity(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches));
    }

    @Test
    void inequalityCheck() {

        Quantity a = new Quantity(1.0, LengthUnit.FEET);
        Quantity b = new Quantity(2.0, LengthUnit.FEET);

        assertFalse(a.equals(b));
    }
}