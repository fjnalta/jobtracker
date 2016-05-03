package net.greenbeansit.jobtracker.server.service;

import java.util.List;

import net.greenbeansit.jobtracker.shared.Employee;

/**
 * Database interface for {@link Employee}s.
 * @author Mike Hukiewitz
 *
 */

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	Employee getEmployee(Long employeeId);
	void saveEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void deleteEmployee(Employee employee);
}
