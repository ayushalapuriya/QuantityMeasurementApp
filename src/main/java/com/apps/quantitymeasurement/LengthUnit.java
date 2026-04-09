public enum LengthUnit implements Unit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double factorToFeet;

    LengthUnit(double factorToFeet) {
        this.factorToFeet = factorToFeet;
    }

    @Override
    public double toBase(double value) {
        return value * factorToFeet;
    }

    @Override
    public double fromBase(double baseValue) {
        return baseValue / factorToFeet;
    }

    @Override
    public String getCategory() {
        return "LENGTH";
    }
}
