package com.apps.quantitymeasurement;

public interface IMeasurable {

    // Mandatory Conversion Methods
    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();

    // UC14 OPTIONAL ARITHMETIC SUPPORT (DEFAULT METHODS)
    SupportsArithmetic supportsArithmetic = () -> true;

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    // operation validation hook
    default void validateOperationSupport(String operation) {
        // Default → all operations allowed
    }
}