package net.greenbeansit.jobtracker.server.data.pseudoJob;

import net.greenbeansit.jobtracker.shared.PseudoJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of PseudoJobDataService {@link PseudoJobDataService}.
 * 
 * @author Mike Hukiewitz
 *
 */

@Service("pseudoJobService")
public class PseudoJobServiceJpa implements PseudoJobDataService {
	
	@Autowired
	private PseudoJobEntityRepository repository;

	@Override
	public List<PseudoJob> getAll() {
		ArrayList<PseudoJob> list = new ArrayList<PseudoJob>();
		for (PseudoJobEntity entity : repository.findAll())
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public List<PseudoJob> getAllByAuthor(Integer author) {
		ArrayList<PseudoJob> list = new ArrayList<PseudoJob>();
		for (PseudoJobEntity entity : repository.findByAuthor(author))
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public PseudoJob getById(Integer pseudoJobId)
	{
		return convert(repository.findById(pseudoJobId));
	}
	
	@Override
	public boolean save(PseudoJob report)
	{
		return repository.save(convert(report)) != null;
	}

	@Override
	public void delete(PseudoJob report)
	{
		repository.delete(convert(report));
	}
	
	private PseudoJob convert(PseudoJobEntity entity)
	{
		if (entity == null)
			return null;
		return new PseudoJob(entity.getId(), entity.getName(), entity.getAuthor());
	}

	private PseudoJobEntity convert(PseudoJob pseudoJob)
	{
		if (pseudoJob == null)
			return null;
		return new PseudoJobEntity(pseudoJob.getName(), pseudoJob.getAuthor());
	}
}
