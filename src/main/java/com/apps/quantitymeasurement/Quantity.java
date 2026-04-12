package com.apps.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private static final double EPSILON = 0.0001;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }

    public U getUnit() { return unit; }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    // ================= EQUALITY =================

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double diff =
                this.toBaseUnit()
                - other.unit.convertToBaseUnit(other.value);

        return Math.abs(diff) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBaseUnit(), unit.getClass());
    }

    // ================= CONVERSION =================

    public Quantity<U> convertTo(U targetUnit) {

        validateTargetUnit(targetUnit);

        double base = toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(round(converted), targetUnit);
    }

    // ================= ADDITION =================

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateOperand(other);
        validateTargetUnit(targetUnit);

        double resultBase =
                this.toBaseUnit()
              + other.toBaseUnit();

        double result =
                targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), targetUnit);
    }

    // ================= UC12 : SUBTRACTION =================

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateOperand(other);
        validateTargetUnit(targetUnit);

        double resultBase =
                this.toBaseUnit()
              - other.toBaseUnit();

        double result =
                targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), targetUnit);
    }

    // ================= UC12 : DIVISION =================

    public double divide(Quantity<U> other) {

        validateOperand(other);

        double divisor = other.toBaseUnit();

        if (Math.abs(divisor) < EPSILON)
            throw new ArithmeticException("Division by zero");

        return this.toBaseUnit() / divisor;
    }

    // ================= VALIDATION =================

    private void validateOperand(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cross-category operation not allowed");
    }

    private void validateTargetUnit(U targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}