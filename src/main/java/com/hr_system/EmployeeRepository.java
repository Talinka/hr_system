package com.hr_system;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query("SELECT new com.hr_system.AverageAgeStatistics(e.position, COUNT(e.name), AVG(age))" +
            " FROM Employee e " +
            "GROUP BY e.position " +
            "ORDER BY COUNT(e.name) DESC")
    public List<AverageAgeStatistics> getEmployeeAverageAges();

 }