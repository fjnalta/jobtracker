package net.greenbeansit.jobtracker.server.data;

import java.util.List;

import net.greenbeansit.jobtracker.shared.User;

/**
 * Database interface for {@link User}s.
 * 
 * @author Mike Hukiewitz & Philipp Minges
 *
 */

public interface UserDataService
{

	List<User> getAll();
	User getUser(Integer employeeId);
	List<User> getByName(String firstname, String lastname);
	List<User> getBySupervisor(Integer supervisorId);
}
