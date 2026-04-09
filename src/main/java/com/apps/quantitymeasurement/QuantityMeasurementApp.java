package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity q1 =
                new Quantity(1, LengthUnit.FEET);

        Quantity q2 =
                new Quantity(12, LengthUnit.INCHES);

        Quantity result =
                Quantity.add(q1, q2, LengthUnit.FEET);

        System.out.println(result);

        Quantity result2 =
                Quantity.add(q1, q2, LengthUnit.INCHES);

        System.out.println(result2);
    }
}
