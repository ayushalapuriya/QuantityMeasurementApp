package com.app.quantitymeasurement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quantity_records")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double value1;
    private String unit1;

    private double value2;
    private String unit2;

    private String operation;
    private double result;

    public QuantityMeasurementEntity() {}

    public QuantityMeasurementEntity(double value1, String unit1,
                                     double value2, String unit2,
                                     String operation,
                                     double result) {
        this.value1 = value1;
        this.unit1 = unit1;
        this.value2 = value2;
        this.unit2 = unit2;
        this.operation = operation;
        this.result = result;
    }

    public Long getId() { return id; }
}