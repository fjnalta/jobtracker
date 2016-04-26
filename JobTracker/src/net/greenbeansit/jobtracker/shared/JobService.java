package net.greenbeansit.jobtracker.shared;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.DirectRestService;

public interface JobService extends DirectRestService
{
	@GET
	@Path("/")
	Job[] getAllJobs();
	
	@GET
	@Path("/{jobid}")
	Job getJob(Long jobid);
}
