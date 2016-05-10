package net.greenbeansit.jobtracker.server.data;

import java.util.List;

import net.greenbeansit.jobtracker.shared.User;

/**
 * Database interface for {@link User}s.
 * 
 * @author Mike Hukiewitz
 *
 */

public interface UserDataService
{

	List<User> getAll();

	User getEmployee(Long employeeId);

	List<User> getByName(String firstname, String lastname);

	void save(User employee);

	void update(User employee);

	void delete(User employee);
}
