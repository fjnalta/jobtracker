package net.greenbeansit.jobtracker.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.HashMap;

import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;

/**
 * Dummy implementation of the {@link ActivityReportTemplateService} interface.
 * 
 * @author Max Blatt
 */
public class ActivityReportTemplateServiceImpl implements IActivityReportTemplateService
{	
	private static Map<Long, ActivityReportTemplate> templateMap;
	
	private Long employeeId;
	
	
	/**
	 * Initializes a new instance of the {@link ActivityReportServiceImpl} class
	 * associated with the {@link Employee} with the following ID.
	 * 
	 * @param employeeId the ID of the {@link Employee}.
	 */
	public ActivityReportTemplateServiceImpl(Long employeeId)
	{
		this.employeeId = employeeId;
		
		if(templateMap == null)
			templateMap = new HashMap<Long, ActivityReportTemplate>();
	}
	
	public ActivityReportTemplate[] getAllTemplates()
	{
		List<ActivityReportTemplate> list = new ArrayList<ActivityReportTemplate>();
		
		for(Long id : templateMap.keySet())
		{
			if(id == employeeId)
				list.add(templateMap.get(id));
		}
		
		return list.toArray(new ActivityReportTemplate[list.size()]);
	}

	public void saveTemplate(ActivityReportTemplate template)
	{
		if(template == null)
			throw new IllegalArgumentException();
		else
		{
			template.setId(createNewTemplateId());
			
			templateMap.put(template.getId(), template);
		}
	}
	
	/**
	 * Generates a new, valid ID for a template.
	 * 
	 * @return a long integer.
	 */
	private static long createNewTemplateId()
	{
		int newId = templateMap.size();
		while(templateMap.containsKey(newId))
			newId++;
		
		return newId;
	}
}
