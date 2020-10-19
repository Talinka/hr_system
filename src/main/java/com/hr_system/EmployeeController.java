package com.hr_system;

import org.springframework.web.bind.annotation.*;

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

}