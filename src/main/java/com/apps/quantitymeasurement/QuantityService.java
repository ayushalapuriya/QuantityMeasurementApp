package com.apps.quantitymeasurement;

public class QuantityService {

    public <U extends IMeasurable> boolean isEqual(
            Quantity<U> q1,
            Quantity<U> q2) {
        return q1.equals(q2);
    }

    public <U extends IMeasurable> Quantity<U> convert(
            Quantity<U> quantity,
            U targetUnit) {
        return quantity.convertTo(targetUnit);
    }

    public <U extends IMeasurable> Quantity<U> add(
            Quantity<U> q1,
            Quantity<U> q2,
            U unit) {
        return q1.add(q2, unit);
    }

    public <U extends IMeasurable> Quantity<U> subtract(
            Quantity<U> q1,
            Quantity<U> q2,
            U unit) {
        return q1.subtract(q2, unit);
    }

    public <U extends IMeasurable> double divide(
            Quantity<U> q1,
            Quantity<U> q2) {
        return q1.divide(q2);
    }
}