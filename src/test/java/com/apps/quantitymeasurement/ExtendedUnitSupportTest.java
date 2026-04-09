package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.UC4ExtendedUnitSupport.Quantity;
import com.apps.quantitymeasurement.UC4ExtendedUnitSupport.Unit;

public class ExtendedUnitSupportTest {

    @Test
    public void testFeetToInchEquality() {
        Quantity q1 = new Quantity(1, Unit.FEET);
        Quantity q2 = new Quantity(12, Unit.INCH);
        assertEquals(q1, q2);
    }

    @Test
    public void testYardToFeetEquality() {
        Quantity q1 = new Quantity(1, Unit.YARD);
        Quantity q2 = new Quantity(3, Unit.FEET);
        assertEquals(q1, q2);
    }

    @Test
    public void testCentimeterToInchEquality() {
        Quantity q1 = new Quantity(2.54, Unit.CENTIMETER);
        Quantity q2 = new Quantity(1, Unit.INCH);
        assertEquals(q1, q2);
    }

    @Test
    public void testDifferentValues() {
        Quantity q1 = new Quantity(1, Unit.FEET);
        Quantity q2 = new Quantity(10, Unit.INCH);
        assertNotEquals(q1, q2);
    }

    @Test
    public void testNullComparison() {
        Quantity q1 = new Quantity(1, Unit.FEET);
        assertNotEquals(q1, null);
    }
}