package net.greenbeansit.jobtracker.shared;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.DirectRestService;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface JobService extends DirectRestService
{
	/**
	 * Gets all {@link Job}s associated to the {@link Employee} of the current
	 * user.
	 * 
	 * @return an array of {@link Job}s. 
	 */
	@GET
	@Path("/")
	Job[] getAllJobs();
	
	/**
	 * Gets the {@link Job} with the given ID.
	 * 
	 * @param jobid the ID of the {@link Job}.
	 * @return a {@link Job}.
	 */
	@GET
	@Path("/{jobid}")
	Job getJob(@PathParam("jobid") Long jobid);
}
