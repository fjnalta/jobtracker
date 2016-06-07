package net.greenbeansit.jobtracker.client.components.manager.detail;

import java.util.List;

import org.fusesource.restygwt.client.Method;

import com.google.gwt.core.client.GWT;

import net.greenbeansit.jobtracker.client.utils.rest.RestClient;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient.SuccessFunction;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.User;

/**
 * Implementation of {@link ManagerEmployeeDetailPageHelperService}.
 * 
 * @author Max Blatt
 */
class ManagerEmployeeDetailPageHelperServiceImpl implements ManagerEmployeeDetailPageHelperService
{
	/**
	 * Callback listener for the {@link ManagerEmployeeDetailPageHelperServiceImpl}.
	 * 
	 * @author Max Blatt.
	 */
	interface Callback
	{
		/**
		 * Is invoked if the data was loaded successfully from the server.
		 */
		void onSuccess();

		/**
		 * Is invoked if the data could not be loaded from the server.
		 * 
		 * @param error
		 *            the {@link Throwable} that describes the error.
		 */
		void onFailure(Throwable error);
	}
	
	private User employee;
	private List<Job> jobs;
	
	/**
	 * Initializes a new instance of the {@link ManagerEmployeeDetailPageHelperServiceImpl} class.
	 * 
	 * @param employeeId the ID of the employee.
	 * @param initCallback
	 *            the {@link Callback} that will be called after the
	 *            asynchronous process has been finished.
	 */
	public ManagerEmployeeDetailPageHelperServiceImpl(final Integer employeeId, final Callback initCallback)
	{
		RestClient.build(new SuccessFunction<User>()
		{
			@Override
			public void onSuccess(Method method, User response)
			{
				employee = response;
				
				RestClient.build(new SuccessFunction<List<Job>>()
				{
					@Override
					public void onSuccess(Method method, List<Job> response)
					{
						jobs = response;
						
						initCallback.onSuccess();
					}

					@Override
					public void onFailure(Method method, Throwable exception)
					{
						GWT.log(exception.getMessage());

						initCallback.onFailure(exception);
					}
					
				}).getEmployeeService().getAllJobs(employeeId);
			}

			@Override
			public void onFailure(Method method, Throwable exception)
			{
				GWT.log(exception.getMessage());

				initCallback.onFailure(exception);
			}
		}).getEmployeeService().getUser(employeeId);
	}
	
	@Override
	public User getEmployee()
	{
		return employee;
	}

	@Override
	public List<Job> getJobs()
	{
		return jobs;
	}

}
