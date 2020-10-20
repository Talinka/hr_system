package com.hr_system;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HrSystemApplication.class)
public class HrSystemApplicationTests {

	@Autowired
	private EmployeeController controller;

	@Autowired
	private EmployeeRepository repository;

	@Test
	public void testGivenGenericEntityRepository() {
		Employee genericEntity = repository
				.save(new Employee("test", 34, "Test position"));
		Optional<Employee> foundEntity = repository
				.findById(genericEntity.getId());

		assertNotNull(foundEntity);
		assertEquals("employees muse be equal", genericEntity.getName(), "test");
	}
}