package net.greenbeansit.jobtracker.server.services;

import net.greenbeansit.jobtracker.shared.Employee;

public interface IEmployeeService {
	
	Employee getEmployee(Long employeeId);
	
}
