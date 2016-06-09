package net.greenbeansit.jobtracker.server.data.utilizationWeek;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.shared.UtilizationWeek;

/**
 * Implements {@link UtilizationWeekDataService}
 * 
 * @author Mike Hukiewitz
 *
 */
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
	public void delete(Integer utilId)
	{
		repository.delete(utilId);
	}
	
//	@Override
//	public List<UtilizationWeek> getByUserAndMonth(Integer authorId,
//			Integer year, Integer month)
//	{
//		ArrayList<UtilizationWeek> list = new ArrayList<UtilizationWeek>();
//		for (UtilizationWeekEntity entity : repository
//				.findByAuthorAndMonth(authorId, year, month))
//		{
//			list.add(convert(entity));
//		}
//		return list;
//	}
//
//	@Override
//	public List<UtilizationWeek> getByUserAndYear(Integer authorId,
//			Integer year)
//	{
//		ArrayList<UtilizationWeek> list = new ArrayList<UtilizationWeek>();
//		for (UtilizationWeekEntity entity : repository
//				.findByAuthorAndYear(authorId, year))
//		{
//			list.add(convert(entity));
//		}
//		return list;
//	}

	/**
	 * Converty the {@link UtilizationWeekEntity} to {@link UtilizationWeek}
	 * 
	 * @param entity
	 *            the {@link UtilizationWeekEntity}
	 * @return the {@link UtilizationWeek}
	 */
	private UtilizationWeek convert(UtilizationWeekEntity entity)
	{
		if (entity == null)
			return null;
		return new UtilizationWeek(entity.getId(), entity.getAuthor(),
				entity.getText(), new java.util.Date(entity.getBeginDate().getTime()), 0,
				0, new java.util.Date(entity.getEndDate().getTime()),
				entity.getPossibilty(), entity.getPseudoJobId(),
				entity.getBreakTime());
	}

	/**
	 * Converts the {@link UtilizationWeek} to {@link UtilizationWeekEntity}
	 * 
	 * @param util
	 *            the {@link UtilizationWeek}
	 * @return the {@link UtilizationWeekEntity}
	 */
	private UtilizationWeekEntity convert(UtilizationWeek util)
	{
		if (util == null)
			return null;
		return new UtilizationWeekEntity(util.getId(), util.getAuthor(),
				util.getText(), new Date(util.getBeginDate().getTime()), 0,
				new Date(util.getEndDate().getTime()), 0, util.getPossibility(),
				util.getBreakTime(), util.getPseudoJobId());
	}
}