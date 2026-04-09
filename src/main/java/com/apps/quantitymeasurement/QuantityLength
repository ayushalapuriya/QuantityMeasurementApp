public final class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 0.0001;

    public QuantityLength(double value, LengthUnit unit) {

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

    // ✅ UC6 ADD METHOD
    public QuantityLength add(QuantityLength other) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        // Convert both to base unit (feet)
        double thisInFeet = this.unit.toBase(this.value);
        double otherInFeet = other.unit.toBase(other.value);

        // Perform addition in base
        double sumInFeet = thisInFeet + otherInFeet;

        // Convert back to first operand unit
        double resultValue = this.unit.fromBase(sumInFeet);

        return new QuantityLength(resultValue, this.unit);
    }

    // Optional: equality with epsilon
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        double thisInFeet = this.unit.toBase(this.value);
        double otherInFeet = other.unit.toBase(other.value);

        return Math.abs(thisInFeet - otherInFeet) < EPSILON;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}