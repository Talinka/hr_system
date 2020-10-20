package com.hr_system;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeRepository repository;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        final String url = "http://localhost:" + port;
        assertThat(this.restTemplate.getForObject(url + "/",
                String.class)).contains("Welcome to HR system");
        // getting all employees
        assertEquals("response contains all records from database",
                this.restTemplate.getForObject(url + "/employees",
                Employee[].class).length, 15);
        // add new employee with post request
        final Employee newEmployee = new Employee("New Employee", 48, "Product owner");
        assertEquals("response contains new employee",
                this.restTemplate.postForObject(url + "/employees", newEmployee, Employee.class).getName(),
                newEmployee.getName());
        assertThat(this.restTemplate.getForObject(url + "/employees",
                Employee[].class).length).isEqualTo(16);
    }

    @Test
    public void testAverageAgeStatistics() throws Exception {
        final String url = "http://localhost:" + port;
        // average age result for this set of employees
        final AverageAgeStatistics[] ageExpectedResult = {
        new AverageAgeStatistics("Frontend developer", 5L, 23.0),
        new AverageAgeStatistics("Backend developer", 4L, 35.0),
        new AverageAgeStatistics("Product owner", 3L, 27.0),
        new AverageAgeStatistics("Scrum master", 2L, 41.0),
        new AverageAgeStatistics("CEO", 1L, 48.0) };

        final ArrayList requestAverageAgeResult = this.restTemplate.getForObject(url + "/employees/average_age", ArrayList.class);
        for (int i = 0; i < 5; i++) {
            final LinkedHashMap map = (LinkedHashMap) requestAverageAgeResult.get(i);
            assertThat(map.get("position")).isEqualTo(ageExpectedResult[i].getPosition());
            assertThat(map.get("count")).isEqualTo(Math.toIntExact(ageExpectedResult[i].getCount()));
            assertThat(map.get("averageAge")).isEqualTo(ageExpectedResult[i].getAverageAge());
        }
    }

    @Test
    public void testAgeGroupsStatistics() throws Exception {
        final String url = "http://localhost:" + port;
        assertThat(this.restTemplate.getForObject(url + "/employees",
                Employee[].class).length).isEqualTo(15);
        // Check age groups
        final String[] groupNames = { "<20", "20-40", "40-60", "60+" };
        final ArrayList requestAgeGroupsResult = this.restTemplate.getForObject(url + "/employees/age_groups", ArrayList.class);
        final AgeGroupStatistics[] groupsExpectedResult = {
                new AgeGroupStatistics(groupNames[0], 5),
                new AgeGroupStatistics(groupNames[1], 6),
                new AgeGroupStatistics(groupNames[2], 3),
                new AgeGroupStatistics(groupNames[3], 1)
        };
        for (int i = 0; i < 4; i++) {
            final LinkedHashMap map = (LinkedHashMap) requestAgeGroupsResult.get(i);
            assertThat(map.get("count")).isEqualTo(groupsExpectedResult[i].getCount());
        }
    }
}