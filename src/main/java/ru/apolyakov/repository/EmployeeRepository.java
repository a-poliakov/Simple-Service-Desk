package ru.apolyakov.repository;


import java.util.List;
import org.springframework.data.repository.CrudRepository;

import ru.apolyakov.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	List<Employee> findByNameContaining(String q);
}