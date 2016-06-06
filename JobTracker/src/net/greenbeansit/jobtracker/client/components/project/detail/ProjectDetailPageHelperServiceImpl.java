package net.greenbeansit.jobtracker.client.components.project.detail;

import org.fusesource.restygwt.client.Method;

import net.greenbeansit.jobtracker.client.utils.rest.RestClient;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient.SuccessFunction;
import net.greenbeansit.jobtracker.shared.Customer;
import net.greenbeansit.jobtracker.shared.Job;

/**
 * Implementation of {@link ProjectDetailPageHelperService}.
 * 
 * @author Max Blatt
 */
class ProjectDetailPageHelperServiceImpl implements ProjectDetailPageHelperService
{
	/**
	 * Callback listener for the REST Services of the
	 * {@link ProjectDetailPageHelperServiceImpl}.
	 * 
	 * @author Max Blatt
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
	
	
	private Job job;
	private Customer customer;
	
	
	/**
	 * Starts the asynchronous loading process.
	 * 
	 * @param jobId the ID of the {@link Job} that should be loaded.
	 * @param initCallback
	 *            the {@link Callback} that will be called after the
	 *            asynchronous process has been finished.
	 */
	public ProjectDetailPageHelperServiceImpl(Integer jobId, final Callback initCallback)
	{
		RestClient.build(new SuccessFunction<Job>()
		{
			@Override
			public void onSuccess(Method method, Job response)
			{
				job = response;
				
				RestClient.build(new SuccessFunction<Customer>()
				{
					@Override
					public void onSuccess(Method method, Customer response)
					{
						customer = response;
						
						initCallback.onSuccess();
					}

					@Override
					public void onFailure(Method method, Throwable exception)
					{
						initCallback.onFailure(exception);
					}
					
				}).getEmployeeService().getCustomer(job.getCustomerID());
			}

			@Override
			public void onFailure(Method method, Throwable exception)
			{
				initCallback.onFailure(exception);
			}
		}).getEmployeeService().getJob(jobId);
		
		
	}

	
	@Override
	public Job getJob()
	{
		return job;
	}

	@Override
	public Customer getCustomer()
	{
		return customer;
	}

}
