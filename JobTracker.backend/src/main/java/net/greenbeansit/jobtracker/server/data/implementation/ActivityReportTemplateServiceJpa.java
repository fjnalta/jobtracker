package net.greenbeansit.jobtracker.server.data.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.server.data.ActivityReportTemplateDataService;
import net.greenbeansit.jobtracker.server.data.entity.ActivityReportTemplateEntity;
import net.greenbeansit.jobtracker.server.data.repository.ActivityReportTemplateEntityRepository;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;

@Service("activityReportTemplateService")
public class ActivityReportTemplateServiceJpa implements ActivityReportTemplateDataService
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
		for (ActivityReportTemplateEntity entity : repository.findByAuthor(author))
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public ActivityReportTemplate getTemplate(Integer templateId)
	{
		return convert(repository.findById(templateId));
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
		return new ActivityReportTemplate(entity.getName(),entity.getDescription(),entity.getId(), entity.getTaskId(), entity.getAuthor());
	}

	private ActivityReportTemplateEntity convert(ActivityReportTemplate report)
	{
		if (report == null)
			return null;
		return new ActivityReportTemplateEntity(report.getId(),report.getTemplateName(),report.getDescription(), report.getTaskId(), report.getAuthor());
	}
}