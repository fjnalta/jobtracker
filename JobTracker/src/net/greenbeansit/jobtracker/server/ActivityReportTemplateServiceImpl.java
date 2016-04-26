package net.greenbeansit.jobtracker.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.dev.util.collect.HashMap;

import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplateService;

public class ActivityReportTemplateServiceImpl implements ActivityReportTemplateService
{	
	private static Map<Long, ActivityReportTemplate> templateMap;
	
	private Long employeeId;
	
	public ActivityReportTemplateServiceImpl(Long employeeId)
	{
		this.employeeId = employeeId;
		
		if(templateMap == null)
			templateMap = new HashMap<Long, ActivityReportTemplate>();
	}
	
	@Override
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

	@Override
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
	
	private long createNewTemplateId()
	{
		int newId = templateMap.size();
		while(templateMap.containsKey(newId))
			newId++;
		
		return newId;
	}
}
