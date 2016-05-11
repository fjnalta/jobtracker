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

	User getUser(Long employeeId);

	List<User> getByName(String firstname, String lastname);
	
	List<User> getBySupervisor(Integer supervisorId);

	boolean save(User employee);

	boolean update(User employee);

	void delete(User employee);
}
