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

    @Query("SELECT CASE " +
            "WHEN (e.age < 20) THEN 0 " +
            "WHEN (e.age < 40) THEN 1 " +
            "WHEN (e.age < 60) THEN 2 " +
            "ELSE 3 " +
            "END AS ageGroupNum " +
            "FROM Employee e ")
    public List<Integer> getEmployeeWithAgeGroup();

}