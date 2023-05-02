package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;

@SpringJUnitConfig(DemoApplication.class)
@SpringBootTest
public class CsvLoaderApplicationTests {

    @Test
    void testClientsBelow30() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(createDataSource());

        List<String> phones = new ArrayList<>();

        String sql = "SELECT phone FROM clients WHERE age < 30";
        try (Connection conn = jdbcTemplate.getDataSource().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                phones.add(rs.getString("phone"));
            }
        }
        //assertEquals(2, phones.size());

// Assert that the correct phones are returned
        assertEquals("+79262853853", phones.get(0));
        assertEquals("+7-916-453-34-12", phones.get(1));
    }

    private static DataSource createDataSource() {
        String url = "jdbc:h2:mem:testdb";
        String username = "sa";
        String password = "";

        return DataSourceBuilder.create().url(url).username(username).password(password).build();
    }
}



