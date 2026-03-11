package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import quantitymeasurement.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class TemperatureMeasurementTest {

    @Test
    void testEquality() {
        Quantity<TemperatureUnit> c =
                new Quantity<>(0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> f =
                new Quantity<>(32, TemperatureUnit.FAHRENHEIT);

        assertTrue(c.equals(f));
    }

    @Test
    void testConversion() {
        Quantity<TemperatureUnit> result =
                new Quantity<>(100, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(212.0, result.getValue());
    }

    @Test
    void testUnsupportedAddition() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.add(t2));
    }

    @Test
    void testCrossCategoryEquality() {

        Quantity<TemperatureUnit> temp =
                new Quantity<>(50, TemperatureUnit.CELSIUS);

        Quantity<LengthUnit> len =
                new Quantity<>(50, LengthUnit.FEET);

        assertFalse(temp.equals(len));
    }
}