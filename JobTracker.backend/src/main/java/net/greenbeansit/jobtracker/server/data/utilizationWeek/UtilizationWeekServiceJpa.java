package net.greenbeansit.jobtracker.server.data.utilizationWeek;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.shared.UtilizationWeek;

@Service("utilizationWeekService")
public class UtilizationWeekServiceJpa implements UtilizationWeekDataService
{

	@Autowired
	private UtilizationWeekEntityRepository repository;

	@Override
	public List<UtilizationWeek> getAll()
	{
		ArrayList<UtilizationWeek> list = new ArrayList<UtilizationWeek>();
		for (UtilizationWeekEntity entity : repository.findAll())
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public UtilizationWeek getUtilizationWeek(Integer utilId)
	{
		return convert(repository.findById(utilId));
	}

	@Override
	public List<UtilizationWeek> getByPeriod(Date from, Date to)
	{
		// TODO Mike: Implement when repository done
		return null;
	}

	@Override
	public List<UtilizationWeek> getByPseudoJob(Integer pseudoJobId)
	{
		ArrayList<UtilizationWeek> list = new ArrayList<UtilizationWeek>();
		for (UtilizationWeekEntity entity : repository
				.findByPseudoJobId(pseudoJobId))
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public List<UtilizationWeek> getByUser(Integer userId)
	{
		ArrayList<UtilizationWeek> list = new ArrayList<UtilizationWeek>();
		for (UtilizationWeekEntity entity : repository.findByAuthor(userId))
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public boolean save(UtilizationWeek util)
	{
		return repository.save(convert(util)) != null;
	}

	@Override
	public void delete(UtilizationWeek util)
	{
		repository.delete(convert(util));
	}

	// TODO change UtilizationWeekEntity Constructor in Backend

	private UtilizationWeek convert(UtilizationWeekEntity entity)
	{
		if (entity == null)
			return null;
		// return new UtilizationWeek(entity.getId(), entity.getAuthor(),
		// entity.getBegin(), entity.getDaysFree(), entity.getDaysWork(),
		// entity.getDaysHoliday(), entity.getPossibilty(),
		// entity.getPseudoJobId());
		return null;
	}

	private UtilizationWeekEntity convert(UtilizationWeek util)
	{
		if (util == null)
			return null;
		// return new UtilizationWeekEntity(util.getId(), util.getAuthor(),
		// util.getBegin(), util.getDaysFree(), util.getDaysWork(),
		// util.getDaysHoliday(), util.getPossibilty(),
		// util.getPseudoJobId());
		return null;
	}
}