public final class Length {

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 0.0001;

    public Length(double value, LengthUnit unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    private double toBase() {
        return unit.toBase(value);
    }

    // 🔥 UC7 ADD WITH TARGET UNIT
    public static Length add(Length a, Length b, LengthUnit targetUnit) {

        if (a == null || b == null)
            throw new IllegalArgumentException("Operands cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double aInFeet = a.unit.toBase(a.value);
        double bInFeet = b.unit.toBase(b.value);

        double sumInFeet = aInFeet + bInFeet;

        double result = targetUnit.fromBase(sumInFeet);

        return new Length(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Length))
            return false;

        Length other = (Length) obj;

        return Math.abs(this.toBase() - other.toBase()) < EPSILON;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}