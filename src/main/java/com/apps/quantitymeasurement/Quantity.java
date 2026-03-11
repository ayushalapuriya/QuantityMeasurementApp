package com.apps.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private static final double EPSILON = 0.0001;

    // CONSTRUCTOR
    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // UC1–UC5 : EQUALITY
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity<?> other))
            return false;

        // Prevent cross-category comparison
        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = ((IMeasurable) other.unit)
                .convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value),
                unit.getClass());
    }

    // UC5 + UC14 : CONVERSION (FIXED)
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        // Prevent cross-category conversion
        if (!unit.getClass().equals(targetUnit.getClass()))
            throw new IllegalArgumentException(
                    "Cannot convert across different measurement categories");

        // STEP 1 → convert to base unit
        double baseValue = unit.convertToBaseUnit(value);

        // STEP 2 → convert from base to target
        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(round(convertedValue), targetUnit);
    }

    // UC13 + UC14 : ARITHMETIC ENUM
    private enum ArithmeticOperation {

        ADD {
            double compute(double a, double b) {
                return a + b;
            }
        },

        SUBTRACT {
            double compute(double a, double b) {
                return a - b;
            }
        },

        DIVIDE {
            double compute(double a, double b) {
                if (Math.abs(b) < EPSILON)
                    throw new ArithmeticException("Division by zero");
                return a / b;
            }
        };

        abstract double compute(double a, double b);
    }

    // VALIDATION HELPERS (UC13)
    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetRequired) {

        if (other == null)
            throw new IllegalArgumentException("Operand cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException(
                    "Different measurement categories");

        if (!Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid value");

        if (targetRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit required");
    }

    // UC14 OPERATION SUPPORT CHECK
    private void validateOperationAllowed(String operation) {
        unit.validateOperationSupport(operation);
    }

    // CENTRALIZED ARITHMETIC (UC13)
    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return operation.compute(base1, base2);
    }

    // ADD
    public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateOperationAllowed("addition");
        validateArithmeticOperands(other, targetUnit, true);

        double resultBase =
                performBaseArithmetic(other, ArithmeticOperation.ADD);

        double converted =
                targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(converted), targetUnit);
    }

    // SUBTRACT
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateOperationAllowed("subtraction");
        validateArithmeticOperands(other, targetUnit, true);

        double resultBase =
                performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double converted =
                targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(converted), targetUnit);
    }

    // DIVIDE
    public double divide(Quantity<U> other) {

        validateOperationAllowed("division");
        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other,
                ArithmeticOperation.DIVIDE);
    }

    // ROUNDING
    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}