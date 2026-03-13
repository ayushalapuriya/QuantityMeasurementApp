package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityController controller = new QuantityController();

        Quantity<LengthUnit> q1 =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12, LengthUnit.INCHES);

        System.out.println(controller.checkEquality(q1, q2));

        Quantity<LengthUnit> result =
                controller.add(q1, q2, LengthUnit.FEET);

        System.out.println(result);

        Quantity<WeightUnit> w1 =
                new Quantity<>(1, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(1000, WeightUnit.GRAM);

        System.out.println(controller.checkEquality(w1, w2));
    }
}