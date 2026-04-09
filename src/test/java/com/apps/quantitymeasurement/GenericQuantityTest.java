package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.UC3GenericQuantity.Quantity;
import com.apps.quantitymeasurement.UC3GenericQuantity.Unit;

public class GenericQuantityTest {

    @Test
    public void testFeetToFeetEquality() {
        Quantity q1 = new Quantity(1, Unit.FEET);
        Quantity q2 = new Quantity(1, Unit.FEET);
        assertEquals(q1, q2);
    }

    @Test
    public void testInchToInchEquality() {
        Quantity q1 = new Quantity(12, Unit.INCH);
        Quantity q2 = new Quantity(12, Unit.INCH);
        assertEquals(q1, q2);
    }

    @Test
    public void testFeetToInchEquality() {
        Quantity q1 = new Quantity(1, Unit.FEET);
        Quantity q2 = new Quantity(12, Unit.INCH);
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

    @Test
    public void testSameReference() {
        Quantity q1 = new Quantity(1, Unit.FEET);
        assertEquals(q1, q1);
    }
}