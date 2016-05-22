package net.greenbeansit.jobtracker.server.data.utilizationWeek;

import java.sql.Date;
import java.util.List;

import net.greenbeansit.jobtracker.shared.UtilizationWeek;

/**
 * Database interface for {@link UtilizationWeek}s.
 * 
 * @author Mike Hukiewitz
 *
 */
public interface UtilizationWeekDataService
{

	List<UtilizationWeek> getAll();

	UtilizationWeek getUtilizationWeek(Integer utilId);

	List<UtilizationWeek> getByPeriod(Date from,
			Date to);

	List<UtilizationWeek> getByUser(Integer authorId);
	
	List<UtilizationWeek> getByPseudoJob(Integer pseudoJobId);

	boolean save(UtilizationWeek util);

	void delete(UtilizationWeek util);
}
