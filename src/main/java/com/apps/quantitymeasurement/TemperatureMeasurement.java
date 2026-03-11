package com.apps.quantitymeasurement;

import quantitymeasurement.model.*;

public class TemperatureMeasurement {

    public static void main(String[] args) {

        Quantity<TemperatureUnit> c =
                new Quantity<>(0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> f =
                new Quantity<>(32, TemperatureUnit.FAHRENHEIT);

        System.out.println("Equality:");
        System.out.println(c.equals(f));

        Quantity<TemperatureUnit> converted =
                new Quantity<>(100, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT);

        System.out.println("Conversion:");
        System.out.println(converted);

        try {
            c.add(f);
        } catch (Exception e) {
            System.out.println("Expected Error:");
            System.out.println(e.getMessage());
        }
    }
}