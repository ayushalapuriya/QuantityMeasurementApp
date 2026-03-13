package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    private final List<QuantityMeasurementEntity> cache = new ArrayList<>();

    @Override
    public void save(QuantityMeasurementEntity entity) {
        cache.add(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return new ArrayList<>(cache);
    }

    @Override
    public int getTotalCount() {
        return cache.size();
    }

    @Override
    public void deleteAll() {
        cache.clear();
    }
}