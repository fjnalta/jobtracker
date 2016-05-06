package net.greenbeansit.jobtracker.server.data;

import java.util.List;

import net.greenbeansit.jobtracker.shared.Employee;

/**
 * Database interface for {@link Employee}s.
 * @author Mike Hukiewitz
 *
 */

public interface EmployeeDataService {
	
	List<Employee> getAll();
	Employee getEmployee(Long employeeId);
	List<Employee> getEmployee(String firstname, String lastname);
	void save(Employee employee);
	void update(Employee employee);
	void delete(Employee employee);
}
