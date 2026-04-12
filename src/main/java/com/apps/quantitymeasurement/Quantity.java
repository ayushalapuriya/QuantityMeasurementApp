package com.apps.quantitymeasurement;

import java.util.Objects;

public final class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private static final double EPSILON = 1e-6;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double toBase() {
        return unit.toBase(value);
    }

    // UC8 GENERIC ADD (UC6 + UC7)
    // public static Quantity add(
    //         Quantity q1,
    //         Quantity q2,
    //         Unit targetUnit) {

    //     if (q1 == null || q2 == null)
    //         throw new IllegalArgumentException("Quantity cannot be null");

    //     if (targetUnit == null)
    //         throw new IllegalArgumentException("Target unit required");

    //     // category validation
    //     if (!q1.unit.getCategory().equals(q2.unit.getCategory()))
    //         throw new IllegalArgumentException("Different categories");

    //     if (!targetUnit.getCategory().equals(q1.unit.getCategory()))
    //         throw new IllegalArgumentException("Invalid target unit");

    //     double baseSum =
    //             q1.unit.toBase(q1.value)
    //                     + q2.unit.toBase(q2.value);

    //     double resultValue =
    //             targetUnit.fromBase(baseSum);

    //     return new Quantity(resultValue, targetUnit);
    // }

    // Equality (UC2–UC5 generic)
    // ---------- Equality ----------
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> other = (Quantity<?>) obj;

        // Prevent length vs weight comparison
        if (this.unit.getClass() != other.unit.getClass())
            return false;

        double thisBase = unit.convertToBaseUnit(value);
        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Objects.hash(Math.round(base / EPSILON));
    }

    // ---------- Conversion ----------
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit null");

        double base = unit.convertToBaseUnit(value);
        double converted =
                targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(round(converted), targetUnit);
    }

    // ---------- Addition ----------
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Null quantity");

        double base1 = unit.convertToBaseUnit(value);
        double base2 =
                other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;

        double result =
                targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(round(result), targetUnit);
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " +
                unit.getUnitName() + ")";
    }
}
