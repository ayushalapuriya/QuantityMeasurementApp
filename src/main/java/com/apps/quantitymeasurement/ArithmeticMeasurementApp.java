package quantitymeasurement.main;

import quantitymeasurement.model.*;

public class ArithmeticMeasurementApp {

    public static void main(String[] args) {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCH);

        System.out.println("Addition:");
        System.out.println(q1.add(q2));

        System.out.println("\nSubtraction:");
        System.out.println(q1.subtract(q2));

        System.out.println("\nDivision:");
        System.out.println(q1.divide(q2));
    }
}