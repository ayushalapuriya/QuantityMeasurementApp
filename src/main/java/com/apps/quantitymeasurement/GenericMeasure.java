package com.apps.quantitymeasurement;

public class GenericMeasure {

    public static void main(String[] args) {

        // ---------- Length ----------
        Quantity<LengthUnit> length1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println("Length Equal: "
                + length1.equals(length2));

        System.out.println("Length Convert: "
                + length1.convertTo(LengthUnit.INCHES));

        System.out.println("Length Add: "
                + length1.add(length2));

        // ---------- Weight ----------
        Quantity<WeightUnit> weight1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("Weight Equal: "
                + weight1.equals(weight2));

        System.out.println("Weight Convert: "
                + weight1.convertTo(WeightUnit.GRAM));

        System.out.println("Weight Add: "
                + weight1.add(weight2));
    }
}