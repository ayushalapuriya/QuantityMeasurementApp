package quantitymeasurement.test;

import org.junit.jupiter.api.Test;
import quantitymeasurement.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticMeasurementTest {

    @Test
    void testAdd() {

        Quantity<LengthUnit> f =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> i =
                new Quantity<>(12, LengthUnit.INCH);

        assertEquals(
                new Quantity<>(2, LengthUnit.FEET),
                f.add(i)
        );
    }

    @Test
    void testSubtract() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(6, LengthUnit.INCH);

        assertEquals(
                new Quantity<>(9.5, LengthUnit.FEET),
                q1.subtract(q2)
        );
    }

    @Test
    void testDivide() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2, LengthUnit.FEET);

        assertEquals(5.0, q1.divide(q2));
    }

    @Test
    void testCrossCategoryFails() {

        Quantity<LengthUnit> l =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<WeightUnit> w =
                new Quantity<>(5, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> l.add((Quantity) w));
    }
}