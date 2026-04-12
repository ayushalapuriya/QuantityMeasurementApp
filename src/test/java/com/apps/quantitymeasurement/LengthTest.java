import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LengthTest {

    @Test
    void testAddition_TargetFeet() {

        Length result = Length.add(
                new Length(1, LengthUnit.FEET),
                new Length(12, LengthUnit.INCHES),
                LengthUnit.FEET);

        assertEquals(new Length(2, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_TargetInches() {

        Length result = Length.add(
                new Length(1, LengthUnit.FEET),
                new Length(12, LengthUnit.INCHES),
                LengthUnit.INCHES);

        assertEquals(new Length(24, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_YardFeet() {

        Length result = Length.add(
                new Length(1, LengthUnit.YARDS),
                new Length(3, LengthUnit.FEET),
                LengthUnit.YARDS);

        assertEquals(new Length(2, LengthUnit.YARDS), result);
    }

    @Test
    void testAddition_NullOperand() {

        assertThrows(IllegalArgumentException.class,
                () -> Length.add(null,
                        new Length(1, LengthUnit.FEET),
                        LengthUnit.FEET));
    }
}