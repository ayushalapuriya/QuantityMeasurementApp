package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.UnitConversion.Quantity;
import com.apps.quantitymeasurement.UnitConversion.Unit;

public class UnitConversionTest {

    @Test
    public void testFeetPlusFeet() {
        Quantity q1 = new Quantity(1, Unit.FEET);
        Quantity q2 = new Quantity(1, Unit.FEET);

        Quantity result = q1.add(q2);

        assertEquals(new Quantity(2, Unit.FEET), result);
    }

    @Test
    public void testFeetPlusInch() {
        Quantity q1 = new Quantity(1, Unit.FEET);
        Quantity q2 = new Quantity(12, Unit.INCH);

        Quantity result = q1.add(q2);

        assertEquals(new Quantity(2, Unit.FEET), result);
    }

    @Test
    public void testYardPlusFeet() {
        Quantity q1 = new Quantity(1, Unit.YARD);
        Quantity q2 = new Quantity(3, Unit.FEET);

        Quantity result = q1.add(q2);

        assertEquals(new Quantity(2, Unit.YARD), result);
    }

    @Test
    public void testCentimeterPlusInch() {
        Quantity q1 = new Quantity(2.54, Unit.CENTIMETER);
        Quantity q2 = new Quantity(1, Unit.INCH);

        Quantity result = q1.add(q2);

        assertEquals(new Quantity(5.08, Unit.CENTIMETER), result);
    }
}