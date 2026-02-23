package com.apps.quantitymeasurement;

public class UnitConversion {

    public enum Unit {

        INCH(1.0),
        FEET(12.0),
        YARD(36.0),
        CENTIMETER(0.393701);

        private final double conversionFactorToInch;

        Unit(double conversionFactorToInch) {
            this.conversionFactorToInch = conversionFactorToInch;
        }

        public double toBaseUnit(double value) {
            return value * conversionFactorToInch;
        }

        public double fromBaseUnit(double baseValue) {
            return baseValue / conversionFactorToInch;
        }
    }

    public static class Quantity {

        private final double value;
        private final Unit unit;

        public Quantity(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return unit.toBaseUnit(value);
        }

        public Quantity add(Quantity other) {

            double totalInBase =
                    this.toBaseUnit() + other.toBaseUnit();

            double resultInCurrentUnit =
                    unit.fromBaseUnit(totalInBase);

            return new Quantity(resultInCurrentUnit, this.unit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Quantity other = (Quantity) obj;

            return Math.abs(
                    this.toBaseUnit() - other.toBaseUnit()
            ) < 0.0001;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBaseUnit());
        }
    }
}