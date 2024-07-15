package com.babalola.smartparkingapplication.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class PostGISInitializerService {

    @Value("${spring.datasource.url}")
    private String databaseUrl;
    @Value("${spring.datasource.username}")
    private String databaseUser;
    @Value("${spring.datasource.password}")
    private String databasePassword;

    @PostConstruct
    public void init() {
        enablePostGIS();
    }

    public void enablePostGIS() {
        try (Connection conn = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
             Statement query = conn.createStatement()) {

            query.execute("CREATE EXTENSION IF NOT EXISTS postgis");
            query.execute("CREATE EXTENSION IF NOT EXISTS postgis_topology");

            System.out.println("enabled postgis extension successfully");

        } catch (SQLException e) {
            System.out.println("unable to enable postgis extension");
            e.printStackTrace();
        }
    }
}
