package net.greenbeansit.jobtracker.shared;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public interface ActivityReportTemplateService
{
	@GET
	@Path("/template")
	ActivityReportTemplate[] getAllTemplates();
	
}
