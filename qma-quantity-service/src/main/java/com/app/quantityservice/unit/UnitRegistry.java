package com.app.quantityservice.unit;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UnitRegistry {

    private final List<Class<? extends Enum<?>>> unitCategories = Arrays.asList(
            LengthUnit.class,
            VolumeUnit.class,
            WeightUnit.class,
            TemperatureUnit.class
    );

    /**
     * Finds the correct unit constant across all measurement categories.
     * @param unitName The string name of the unit (e.g., "LITRE", "INCHES")
     * @return The IMeasurable unit instance
     * @throws IllegalArgumentException if the unit is not found in any category
     */
    public IMeasurable getUnit(String unitName) {
        if (unitName == null || unitName.isBlank()) {
            throw new IllegalArgumentException("Unit name cannot be empty");
        }

        String searchName = unitName.toUpperCase().trim();

        for (Class<? extends Enum<?>> category : unitCategories) {
            try {
                return resolveUnit(category, searchName);
            } catch (IllegalArgumentException ignored) {
                // Move to the next category if not found in this one.
            }
        }

        throw new IllegalArgumentException("Invalid unit: '" + unitName +
                "'. Ensure the unit exists in Length, Volume, Weight, or Temperature categories.");
    }

    @SuppressWarnings("unchecked")
    private <T extends Enum<T> & IMeasurable> IMeasurable resolveUnit(
            Class<? extends Enum<?>> category,
            String searchName) {
        return Enum.valueOf((Class<T>) category.asSubclass(Enum.class), searchName);
    }
}
