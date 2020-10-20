package com.hr_system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    // prepare data for testing
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            repository.save(new Employee("Anry Dogger", 48, "CEO"));
            repository.save(new Employee("Amira Jerde", 28, "Backend developer"));
            repository.save(new Employee("Ms. Maricela Renner", 18, "Product owner"));
            repository.save(new Employee("Benny Dickens DVM", 16, "Scrum master"));
            repository.save(new Employee("Chase Kessler", 49, "Backend developer"));
            repository.save(new Employee("Elbert Fay", 17, "Backend developer"));
            repository.save(new Employee("Ardith Ziemann", 26, "Frontend developer"));
            repository.save(new Employee("Aletha Boehm III", 25, "Product owner"));
            repository.save(new Employee("Frank Tremblay III", 18, "Frontend developer"));
            repository.save(new Employee("Edwardo Simonis", 39, "Product owner"));
            repository.save(new Employee("Brande Collins", 13, "Frontend developer"));
            repository.save(new Employee("Sherryl West Jr.", 45, "Backend developer"));
            repository.save(new Employee("Sherryl Daugherty", 27, "Frontend developer"));
            repository.save(new Employee("Ms. Victor Rice", 29, "Frontend developer"));
            repository.save(new Employee("Celsa Prohaska", 65, "Scrum master"));
        };
    }
}

