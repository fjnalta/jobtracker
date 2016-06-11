package net.greenbeansit.jobtracker.shared.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.greenbeansit.jobtracker.shared.*;

import org.fusesource.restygwt.client.DirectRestService;

/**
 * The service for all server fuctions
 *
 * @author Max Blatt & Alexander Kirilyuk & Philipp Minges
 */
@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface RestService extends DirectRestService
{
	/**
	 * Gets all {@link User}.
	 *
	 * @return a List of {@link User}s.
	 * @throws NotFoundException
	 *             if there are no Users.
	 */
	@GET
	@Path("/users")
	List<User> getAllUser();

	/**
	 * Gets the {@link User} with the following ID.
	 *
	 * @param userId
	 *            the ID of the searched {@link User}.
	 * @return an {@link User}.
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/users/{userId}")
	User getUser(@PathParam("userId") Integer userId);

	/**
	 * Gets all {@link Job} made by the {@link User} with the following ID.
	 *
	 * @param userId
	 *            the ID of the {@link User}.
	 * @return a List of {@link Job}s.
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/users/{userId}/jobs")
	List<Job> getAllJobs(@PathParam("userId") Integer userId);

	/**
	 * Gets all {@link ActivityReport}s made by the {@link User} with the
	 * following ID.
	 *
	 * @param userId
	 *            the ID of the {@link User}.
	 * @return a List of {@link ActivityReport}s.
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/users/{userId}/reports")
	List<ActivityReport> getAllReports(@PathParam("userId") Integer userId);

	/**
	 * Gets all {@link UtilizationWeek}s made by the {@link User} with the
	 * following ID.
	 *
	 * @param userId
	 *            the ID of the {@link User}.
	 * @return a List of {@link UtilizationWeek}s.
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/users/{userId}/utilization_week")
	List<UtilizationWeek> getAllUtilizationWeeks(
			@PathParam("userId") Integer userId);

	/**
	 * Gets a {@link UtilizationWeek} by its ID.
	 * 
	 * @param userId
	 *            its author
	 * @param utilId
	 *            its ID
	 * @return a {@link UtilizationWeek}
	 */
	@GET
	@Path("/users/{userId}/utilization_week/{utilId}")
	UtilizationWeek getSingleUtilizationWeek(
			@PathParam("userId") Integer userId,
			@PathParam("utilId") Integer utilId);

	/**
	 * Gets all {@link ActivityReport}s made by the {@link User} with the
	 * following ID.
	 *
	 * @param userId
	 *            the ID of the {@link User}.
	 * @param reportId
	 *            the ID of the {@link ActivityReport}.
	 * @return the specific {@link ActivityReport} with the given reportId.
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/users/{userId}/reports/{reportId}")
	ActivityReport getReport(@PathParam("userId") Integer userId,
			@PathParam("reportId") Integer reportId);

	/**
	 * Gets all {@link ActivityReport}s made by the {@link User} with the
	 * following ID that are in the following period of time.
	 * <p>
	 * The pattern of the date is "yyyy_mm_dd-yyyy_mm_dd"
	 *
	 * @param userId
	 *            the ID of the {@link User}.
	 * @param fromto
	 *            the start and end date.
	 * @return List of {@link ActivityReport}.
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/users/{userId}/reportPeriod/{from-to}")
	List<ActivityReport> getReportPeriod(@PathParam("userId") Integer userId,
			@PathParam("from-to") String fromto);

	/**
	 * Gets all {@link Customer}s
	 *
	 * @throws NotFoundException
	 *             if there are no Customers.
	 * @return List of all customers
	 */
	@GET
	@Path("/customers")
	List<Customer> getAllCustomer();

	/**
	 * Gets a {@link Customer} by its ID
	 *
	 * @param id
	 *            the ID of the {@link Customer}.
	 * @return the customer
	 */
	@GET
	@Path("/customers/id/{id}")
	Customer getCustomer(@PathParam("id") Integer id);

	/**
	 * Gets a {@link Customer} by its name.
	 * 
	 * @param name
	 *            name of the customer
	 * @return the customer
	 */
	@GET
	@Path("/customers/{name}")
	Customer getCustomer(@PathParam("name") String name);

	/**
	 * Gets the {@link Job} with the following ID.
	 * 
	 * @param jobNo
	 *            3 to 6 digits
	 * @param posNo
	 *            up to 3 digits
	 * 
	 * @return the searched {@link Job} if it was found. Otherwise null.
	 */
	@GET
	@Path("/jobs/{jobNo}/{posNo}")
	Job getJob(@PathParam("jobNo") Integer jobNo,
			@PathParam("posNo") Integer posNo);

	/**
	 * Saves an {@link ActivityReport} to the database.
	 *
	 * @param userId
	 *            its author
	 * @param report
	 *            the report
	 */
	@POST
	@Path("/users/{userId}/reports/")
	void saveReport(@PathParam("userId") Integer userId, ActivityReport report);

	/**
	 * Saves an {@link UtilizationWeek} to the database.
	 *
	 * @param userId
	 *            its author
	 * @param utilweek
	 *            the {@link UtilizationWeek}
	 */
	@POST
	@Path("/users/{userId}/utilization_week")
	void saveUtilizationWeek(@PathParam("userId") Integer userId,
			UtilizationWeek utilweek);

	/**
	 * Deletes an {@link UtilizationWeek} from the database.
	 * 
	 * @param userId
	 *            ID of its author
	 * @param utilId
	 *            ID of the {@link UtilizationWeek}
	 */
	@DELETE
	@Path("/users/{userId}/utilization_week/{utilId}")
	void deleteUtilizationWeek(@PathParam("userId") Integer userId,
			@PathParam("utilId") Integer utilId);

	/**
	 * Deletes an {@link ActivityReport} from the database.
	 * 
	 * @param userId
	 *            its author
	 * @param reportId
	 *            its ID
	 */
	@DELETE
	@Path("/users/{userId}/reports/{reportId}")
	void deleteReport(@PathParam("userId") Integer userId,
			@PathParam("reportId") Integer reportId);

	/**
	 * Gets all {@link ActivityReportTemplate}s of the {@link User} with the
	 * following ID.
	 *
	 * @param userId
	 *            the ID of the {@link User}.
	 * @return a List of {@link ActivityReportTemplate}s.
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/users/{userId}/report_templates")
	List<ActivityReportTemplate> getAllReportTemplates(
			@PathParam("userId") Integer userId);

	/**
	 * Retrieves all users and their role as {@link UserJob} entities for a
	 * specific job.
	 *
	 * @param jobNo
	 *            3 to 6 digits job number.
	 * @param posNo
	 *            3 digit position number.
	 * @return a List of {@link UserJob}s.
	 */
	@GET
	@Path("/usertojob/{jobNo}&{posNo}")
	List<User> getUsersToJob(@PathParam("jobNo") Integer jobNo,
			@PathParam("posNo") Integer posNo);

	/**
	 * Returns the utilization of an employee for the given month.
	 *
	 * @param userId
	 *            user
	 * @param year
	 *            year
	 * @param month
	 *            month
	 * @return percent value (0-100 (and even more))
	 */
	@GET
	@Path("/users/{userId}/utilization/{year}/{month}")
	Integer getUtilizationMonth(@PathParam("userId") Integer userId,
			@PathParam("year") Integer year, @PathParam("month") Integer month);

	/**
	 * Returns the utilization of an employee as a {@link List} for each day for
	 * the given month.
	 *
	 * @param userId
	 *            user
	 * @param year
	 *            year
	 * @param month
	 *            month
	 * @return List of Integer where the index is the corresponding day
	 */
	@GET
	@Path("/users/{userId}/utilizationDays/{year}/{month}")
	List<Integer> getUtilizationDays(@PathParam("userId") Integer userId,
			@PathParam("year") Integer year, @PathParam("month") Integer month);

	/**
	 * Returns the utilization of an employee as a {@link List} for each day for
	 * the given month.
	 *
	 * @param userId
	 *            user
	 * @param year
	 *            year
	 * @return List of Integer where the index is the corresponding day
	 */
	@GET
	@Path("/users/{userId}/utilizationMonths/{year}")
	List<Integer> getUtilizationMonths(@PathParam("userId") Integer userId,
			@PathParam("year") Integer year);

	/**
	 * Returns the utilization of an employee for the given year.
	 *
	 * @param userId
	 *            user
	 * @param year
	 *            year
	 * @return percent value (0-100 (and even more))
	 */
	@GET
	@Path("/users/{userId}/utilization/{year}")
	Integer getUtilizationYear(@PathParam("userId") Integer userId,
			@PathParam("year") Integer year);

	/**
	 * Saves an {@link ActivityReportTemplate} with a new ID for the
	 * {@link User}
	 *
	 * @param userId
	 *            the ID of {@link User}
	 * @param template
	 *            the {@link ActivityReportTemplate} that should be saved.
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@POST
	@Path("/users/{userId}/report_templates/")
	void saveReportTemplate(@PathParam("userId") Integer userId,
			ActivityReportTemplate template);

	/**
	 * Gets the employees who have the user with the following ID as supervisor.
	 * Additionally all {@link User}s will have their List assignedJobs filled
	 * with their assigned jobs.
	 *
	 * @param supervisorId
	 *            the ID of the supervisor user.
	 * @return a {@link ManagerPageRestServiceResponse}.
	 */
	@GET
	@Path("manager/{supervisorId}/employees")
	ManagerPageRestServiceResponse getEmployees(
			@PathParam("supervisorId") Integer supervisorId);

	/**
	 * Gets all {@link Job}s of the user with the following ID and the union of
	 * all associated customers.
	 *
	 * @param userId
	 *            the ID of the user.
	 * @return a {@link ProjectPageRestServiceResponse}.
	 */
	@GET
	@Path("project/{userId}/jobs")
	ProjectPageRestServiceResponse getProjectPageData(
			@PathParam("userId") Integer userId);

	/**
	 * (De-)Activates the budget lock on a given job.
	 * 
	 * @param jobNo
	 *            3 to 6 digits
	 * @param posNo
	 *            3 digits
	 * @param lock
	 *            true when enabling the lock, false when disabling
	 */
	@PUT
	@Path("jobs/{jobNo}&{posNo}/lock/{bool}")
	void setJobLock(@PathParam("jobNo") Integer jobNo,
			@PathParam("posNo") Integer posNo, @PathParam("bool") boolean lock);

	/**
	 * Gets all {@link PseudoJob}s written by given user.
	 * 
	 * @param userId
	 *            ID of the user
	 * @return List of his pseudoJobs
	 */
	@GET
	@Path("users/{userId}/pseudoJobs")
	List<PseudoJob> getAllPseudoJobs(@PathParam("userId") Integer userId);

	/**
	 * Saves a {@link PseudoJob} to the database.
	 * 
	 * @param userId
	 *            ID of its author
	 * @param pseudoJob
	 *            the entity
	 */
	@POST
	@Path("users/{userId}/pseudoJobs/")
	void savePseudoJob(@PathParam("userId") Integer userId,
			PseudoJob pseudoJob);

	/**
	 * Deletes a {@link PseudoJob} from the database.
	 * 
	 * @param userId
	 *            ID of its author
	 * @param pseudoJobId
	 *            ID of the pseudo job to delete
	 */
	@DELETE
	@Path("users/{userId}/pseudoJobs/{pseudoJobId}")
	void deletePseudoJob(@PathParam("userId") Integer userId,
			@PathParam("pseudoJobId") Integer pseudoJobId);

	/**
	 * Removes the specified {@link ActivityReportTemplate} from the database.
	 *
	 * @param author
	 *            author of the template.
	 * @param name
	 *            name of the template.
	 */
	@DELETE
	@Path("/users/{userId}/report_templates/{templateName}")
	void deleteReportTemplate(@PathParam("userId") Integer author,
			@PathParam("templateName") String name);

	/**
	 * Gets all {@link JobTask}s available to a given user.
	 * 
	 * @param user
	 *            ID of the user
	 * @return a List of {@link JobTask}s
	 * @throws NotFoundException
	 *             if there are no {@link JobTask}s
	 */
	@GET
	@Path("/users/{userId}/jobTasks")
	List<JobTask> getJobTasksOfUser(@PathParam("userId") Integer user);
	
	/**
	 * Gets all {@link JobTask}s of the following {@link Job}.
	 * 
	 * @param jobNo the job number.
	 * @param posNo the position number.
	 * @return a list of {@link JobTask}s.
	 */
	List<JobTask> getJobTasks(@PathParam("jobNo") Integer jobNo,
			@PathParam("posNo") Integer posNo);

	/**
	 * Retrieves a list of total used budget values for each month in a year for
	 * a given job number.
	 * 
	 * @param jobNo
	 *            3 to 6 digits
	 * @param posNo
	 *            up to 3 digits
	 * @param year
	 *            the year
	 * @return List of total budget values. Months start at index 1. Index 0 is
	 *         zero.
	 */
	@GET
	@Path("/jobs/{jobNo}&{posNo}/budget/{year}")
	List<Integer> getUsedBudgedYear(@PathParam("jobNo") Integer jobNo,
			@PathParam("posNo") Integer posNo, @PathParam("year") Integer year);

	/**
	 * Retrieves a list of total used budget value for each day in a month for a
	 * given job number.
	 * 
	 * @param jobNo
	 *            3 to 6 digits
	 * @param posNo
	 *            up to 3 digits
	 * @param year
	 *            the year
	 * @param month
	 *            the month
	 * @return List of total budget values. Days start at index 1. Index 0 is
	 *         zero.
	 */
	@GET
	@Path("/jobs/{jobNo}&{posNo}/budget/{year}/{month}")
	List<Integer> getUsedBudgedMonth(@PathParam("jobNo") Integer jobNo,
			@PathParam("posNo") Integer posNo, @PathParam("year") Integer year,
			@PathParam("month") Integer month);
}
