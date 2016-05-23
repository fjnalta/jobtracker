package net.greenbeansit.jobtracker.server.data.user;

import java.sql.Date;
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

	/**
	 * Returns all existing Users.
	 * @return Users in database
	 */
	List<User> getAll();
	
	/**
	 * Returns a User by his ID.
	 * @param employeeId ID of requested User
	 * @return shared User object
	 */
	User getUser(Integer employeeId);
	
	/**
	 * Returns the first User found with the given name. If there are multiple Users with the given name, only one of them will be returned.
	 * @param firstname their firstname
	 * @param lastname their lastname
	 * @return first shared User object
	 */
	User getByName(String firstname, String lastname);
	
	/**
	 * Returns a list of users, who are supervised by a user with the given ID.
	 * @param supervisorId ID of their supervisor
	 * @return List of shared User objects
	 */
	List<User> getBySupervisor(Integer supervisorId);
	
	/**
	 * Returns the utilization of the given User.
	 * @param employeeId ID of User
	 * @param from from which date included 
	 * @param to to which date included
	 * @return utilization percentage
	 */
	Integer getUtilization(Integer employeeId, Date from, Date to);
}