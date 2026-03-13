package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.exception.DatabaseException;
import com.apps.quantitymeasurement.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {

    private static final String INSERT =
            "INSERT INTO quantity_measurement(operation,type,value1,value2,result) VALUES(?,?,?,?,?)";

    @Override
    public void save(QuantityMeasurementEntity entity) {

        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT)) {

            ps.setString(1, entity.getOperation());
            ps.setString(2, entity.getMeasurementType());
            ps.setDouble(3, entity.getValue1());
            ps.setDouble(4, entity.getValue2());
            ps.setDouble(5, entity.getResult());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Save failed", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {

        List<QuantityMeasurementEntity> list = new ArrayList<>();

        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps =
                     con.prepareStatement("SELECT * FROM quantity_measurement");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new QuantityMeasurementEntity(
                        rs.getString("operation"),
                        rs.getString("type"),
                        rs.getDouble("value1"),
                        rs.getDouble("value2"),
                        rs.getDouble("result")
                ));
            }

        } catch (Exception e) {
            throw new DatabaseException("Fetch failed", e);
        }

        return list;
    }

    @Override
    public int getTotalCount() {

        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps =
                     con.prepareStatement("SELECT COUNT(*) FROM quantity_measurement");
             ResultSet rs = ps.executeQuery()) {

            rs.next();
            return rs.getInt(1);

        } catch (Exception e) {
            throw new DatabaseException("Count failed", e);
        }
    }

    @Override
    public void deleteAll() {

        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps =
                     con.prepareStatement("DELETE FROM quantity_measurement")) {

            ps.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Delete failed", e);
        }
    }
}