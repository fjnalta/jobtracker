package net.greenbeansit.jobtracker.shared;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ActivityReportTemplateService
{
	@GET
	@Path("/")
	ActivityReportTemplate[] getAllTemplates();
	
	@POST
	@Path("/")
	void saveTemplate(ActivityReportTemplate template);
}
