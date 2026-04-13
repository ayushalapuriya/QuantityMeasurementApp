package com.apps.quantitymeasurement;

public class WeightMeasurement {

    public static void main(String[] args) {

        QuantityWeight w1 =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight w2 =
                new QuantityWeight(1000.0, WeightUnit.GRAM);

        // Equality
        System.out.println("Equal: " + w1.equals(w2));

        // Conversion
        QuantityWeight converted =
                w1.convertTo(WeightUnit.GRAM);

        System.out.println("Converted: " + converted);

        // Addition (default unit)
        QuantityWeight sum = w1.add(w2);
        System.out.println("Sum: " + sum);

        // Addition (explicit unit)
        QuantityWeight sumInPound =
                w1.add(w2, WeightUnit.POUND);

        System.out.println("Sum in Pound: " + sumInPound);
    }
}