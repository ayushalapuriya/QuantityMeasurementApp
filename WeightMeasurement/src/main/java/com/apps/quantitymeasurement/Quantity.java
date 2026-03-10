package com.apps.quantitymeasurement;

public final class Quantity {

    private final double value;
    private final Unit unit;

    private static final double EPSILON = 0.0001;

    public Quantity(double value, Unit unit) {

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

    public Unit getUnit() {
        return unit;
    }

    private double toBase() {
        return unit.toBase(value);
    }

    // ===============================
    // UC8 GENERIC ADD (UC6 + UC7)
    // ===============================
    public static Quantity add(
            Quantity q1,
            Quantity q2,
            Unit targetUnit) {

        if (q1 == null || q2 == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit required");

        // category validation
        if (!q1.unit.getCategory().equals(q2.unit.getCategory()))
            throw new IllegalArgumentException("Different categories");

        if (!targetUnit.getCategory().equals(q1.unit.getCategory()))
            throw new IllegalArgumentException("Invalid target unit");

        double baseSum =
                q1.unit.toBase(q1.value)
                        + q2.unit.toBase(q2.value);

        double resultValue =
                targetUnit.fromBase(baseSum);

        return new Quantity(resultValue, targetUnit);
    }

    // ===============================
    // Equality (UC2–UC5 generic)
    // ===============================
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity))
            return false;

        Quantity other = (Quantity) obj;

        if (!unit.getCategory()
                .equals(other.unit.getCategory()))
            return false;

        return Math.abs(
                this.toBase() - other.toBase()
        ) < EPSILON;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}