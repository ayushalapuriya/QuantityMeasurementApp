package com.apps.quantitymeasurement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayDeque;
import java.util.Queue;

public class ConnectionPool {

    private static final Queue<Connection> pool = new ArrayDeque<>();
    private static final int SIZE = 5;

    static {
        try {
            for (int i = 0; i < SIZE; i++) {
                pool.add(create());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection create() throws Exception {
        return DriverManager.getConnection(
                ApplicationConfig.getDbUrl(),
                ApplicationConfig.getUsername(),
                ApplicationConfig.getPassword());
    }

    public static synchronized Connection getConnection() throws Exception {
        return pool.isEmpty() ? create() : pool.poll();
    }

    public static synchronized void release(Connection con) {
        pool.offer(con);
    }
}