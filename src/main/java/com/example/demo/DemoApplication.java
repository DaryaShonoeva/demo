package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);

        String csvFile = "src/main/resources/Clients.csv";
        String line = "";
        String cvsSplitBy = ",";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] fields = line.split(cvsSplitBy);

                String name = fields[0];
                int age = Integer.parseInt(fields[1]);
                int groupId = Integer.parseInt(fields[2]);
                String phone = fields[3];
                java.util.Date date = null;
                if (fields[4] != null && !fields[4].isEmpty()) {
                    date = df.parse(fields[4]);
                }

                System.out.println(name + ' ' + age + ' ' + groupId);
                jdbcTemplate.update("INSERT INTO clients (name, age, group_id, phone, date) VALUES (?, ?, ?, ?, ?)",
                        name, age, groupId, phone, date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

