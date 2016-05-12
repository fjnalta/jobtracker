package net.greenbeansit.jobtracker.server.data.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.server.data.UserDataService;
import net.greenbeansit.jobtracker.server.data.entity.UserEntity;
import net.greenbeansit.jobtracker.server.data.repository.UserEntityRepository;
import net.greenbeansit.jobtracker.shared.User;

@Service("userService")
public class UserServiceJpa implements UserDataService {
	
	@Autowired
	private UserEntityRepository repository;

	@Override
	public List<User> getAll() {
		ArrayList<User> list = new ArrayList<User>();
		for (UserEntity entity : repository.findAll())
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public User getUser(Integer id) {
		return convert(repository.findOne(id));
	}

	@Override
	public List<User> getByName(String firstname, String lastname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getBySupervisor(Integer supervisorId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private User convert(UserEntity entity)
	{
		if (entity == null)
			return null;
		return new User(entity.getId(), entity.getName(),
				entity.getSurname(), entity.getSupervisor());
	}

//	private JobEntity convert(User user)
//	{
//		if (user == null)
//			return null;
//		return new JobEntity(job.getJobNr(), job.getPosNr(), job.getPayMode(),
//				job.getCustomerID(), job.getDesc(), job.getMaxBudget(),
//				job.getUsedBudget());
//	}
}
