package com.apps.quantitymeasurement;

public interface Unit {

    // convert value to base unit
    double toBase(double value);

    // convert base value to this unit
    double fromBase(double baseValue);

    // measurement category
    String getCategory();
}
