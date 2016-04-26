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
	/**
	 * Gets all templates created by the user.
	 * 
	 * @return an array of {@link ActivityReportTemplate}s.
	 */
	@GET
	@Path("/")
	ActivityReportTemplate[] getAllTemplates();
	
	/**
	 * Saves an {@link ActivityReportTemplate} with a new ID.
	 * 
	 * @param template the {@link ActivityReportTemplate} that should be saved.
	 */
	@POST
	@Path("/")
	void saveTemplate(ActivityReportTemplate template);
}
