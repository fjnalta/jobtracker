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
	@GET
	@Path("/")
	Job[] getAllJobs();
	
	@GET
	@Path("/{jobid}")
	Job getJob(@PathParam("jobid") Long jobid);
}
