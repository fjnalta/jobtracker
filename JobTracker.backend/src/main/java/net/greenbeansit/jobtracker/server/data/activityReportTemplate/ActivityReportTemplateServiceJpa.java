package net.greenbeansit.jobtracker.server.data.activityReportTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;

/**
 * Implements {@link ActivityReportTemplateDataService}
 * 
 * @author Mike Hukiewitz
 *
 */
@Service("activityReportTemplateService")
public class ActivityReportTemplateServiceJpa
		implements ActivityReportTemplateDataService
{

	@Autowired
	private ActivityReportTemplateEntityRepository repository;

	@Override
	public List<ActivityReportTemplate> getAll()
	{
		ArrayList<ActivityReportTemplate> list = new ArrayList<ActivityReportTemplate>();
		for (ActivityReportTemplateEntity entity : repository.findAll())
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public List<ActivityReportTemplate> getBy(Integer author)
	{
		ArrayList<ActivityReportTemplate> list = new ArrayList<ActivityReportTemplate>();
		for (ActivityReportTemplateEntity entity : repository
				.findByAuthor(author))
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public ActivityReportTemplate getTemplate(Integer author, String name)
	{
		return convert(repository.findByAuthorAndName(author, name));
	}

	@Override
	public boolean save(ActivityReportTemplate report)
	{
		return repository.save(convert(report)) != null;
	}

	@Override
	public void delete(ActivityReportTemplate report)
	{
		repository.delete(convert(report));
	}

	private ActivityReportTemplate convert(ActivityReportTemplateEntity entity)
	{
		if (entity == null)
			return null;
		return new ActivityReportTemplate(entity.getName(), entity.getText(),
				entity.getTaskId(), entity.getAuthor());
	}

	private ActivityReportTemplateEntity convert(ActivityReportTemplate report)
	{
		if (report == null)
		{
			return null;
		}
		return new ActivityReportTemplateEntity(report.getName(),
				report.getText(), report.getTaskId(), report.getAuthor());
	}
}