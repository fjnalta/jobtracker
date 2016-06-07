package net.greenbeansit.jobtracker.client.components.manager.detail;

import org.fusesource.restygwt.client.Method;

import com.google.gwt.core.client.GWT;

import net.greenbeansit.jobtracker.client.utils.rest.RestClient;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient.SuccessFunction;
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
	
	
	/**
	 * Initializes a new instance of the {@link ManagerEmployeeDetailPageHelperServiceImpl} class.
	 * 
	 * @param employeeId the ID of the employee.
	 * @param initCallback
	 *            the {@link Callback} that will be called after the
	 *            asynchronous process has been finished.
	 */
	public ManagerEmployeeDetailPageHelperServiceImpl(Integer employeeId, final Callback initCallback)
	{
		RestClient.build(new SuccessFunction<User>()
		{
			@Override
			public void onSuccess(Method method, User response)
			{
				employee = response;
				
				initCallback.onSuccess();
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

}
