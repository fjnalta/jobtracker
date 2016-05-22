package net.greenbeansit.jobtracker.server.data.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.shared.User;

/**
 * Implementation of UserDataService {@link UserDataService}.
 * 
 * @author Philipp Minges
 *
 */

@Service("userService")
public class UserServiceJpa implements UserDataService
{

	@Autowired
	private UserEntityRepository repository;

	@Override
	public List<User> getAll()
	{
		ArrayList<User> list = new ArrayList<User>();
		for (UserEntity entity : repository.findAll())
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public User getUser(Integer id)
	{
		return convert(repository.findOne(id));
	}

	@Override
	public User getByName(String firstname, String lastname)
	{
		return convert(repository.findByName(firstname, lastname));
	}

	@Override
	public List<User> getBySupervisor(Integer supervisorId)
	{
		ArrayList<User> list = new ArrayList<User>();
		for (UserEntity entity : repository.findBySupervisor(supervisorId))
		{
			list.add(convert(entity));
		}
		return list;
	}

	private User convert(UserEntity entity)
	{
		if (entity == null)
			return null;
		return new User(entity.getId(), entity.getName(), entity.getSurname(),
				entity.getSupervisor());
	}

//	Not needed
	
//	private UserEntity convert(User user)
//	{
//		if (user == null)
//			return null;
//		return new UserEntity(user.getId(), user.getName(), user.getSurname(),
//				user.getSupervisor());
//	}
}
