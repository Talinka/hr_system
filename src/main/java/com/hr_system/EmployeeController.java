package com.hr_system;

import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class EmployeeController {
    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return repository.findAll();
    }

    @PostMapping("/employees")
    public void newEmployee(@RequestBody Employee newEmployee) {
        repository.save(newEmployee);
    }

    @DeleteMapping("/employees")
    public void deleteEmployee(@RequestBody Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    // Count and average age of employees at every position
    @GetMapping("/employees/average_age")
    public Iterable<AverageAgeStatistics> getEmployeeAverageAges() {
        return repository.getEmployeeAverageAges();
    }

    // Count of employees by age in categories <20 years, 20-40, 40-60, 60+
    @GetMapping("/employees/age_groups")
    public AgeGroupStatistics[] getEmployeeAgeGroups() {
        final List<Integer> groups = repository.getEmployeeWithAgeGroup();
        final Integer groupCount = 4;
        final String[] groupNames = { "<20", "20-40", "40-60", "60+" };
        final AgeGroupStatistics[] statistics = new AgeGroupStatistics[groupCount];
        for (int i = 0; i < groupCount; i++) {
            statistics[i] = new AgeGroupStatistics(groupNames[i], Collections.frequency(groups, i));
        }
        return statistics;
    }

}