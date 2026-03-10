package com.apps.quantitymeasurement;

public class ArithmeticOperations {

    public static void main(String[] args) {

        Quantity<LengthUnit> l1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> l2 =
                new Quantity<>(6.0, LengthUnit.INCHES);

        // Subtraction (implicit)
        System.out.println(l1.subtract(l2));

        // Subtraction (explicit)
        System.out.println(
                l1.subtract(l2, LengthUnit.INCHES)
        );

        // Division
        Quantity<LengthUnit> l3 =
                new Quantity<>(2.0, LengthUnit.FEET);

        System.out.println(
                "Division = " + l1.divide(l3)
        );

        // Volume example
        Quantity<VolumeUnit> v1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        System.out.println(v1.subtract(v2));
        System.out.println(v1.divide(v2));
    }
}