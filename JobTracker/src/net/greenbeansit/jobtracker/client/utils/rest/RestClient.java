package net.greenbeansit.jobtracker.client.utils.rest;

import org.fusesource.restygwt.client.DirectRestService;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.callback.CallbackAware;

import com.google.gwt.core.client.GWT;

import net.greenbeansit.Jobtracker.shared.*;

/**
 * Client for backend service calls.
 * 
 * @author Fethullah Misir
 * @param <R>
 */
public class RestClient<R> {

	private static EmployeeService EMPLOYEE_SERVICE = GWT.create(EmployeeService.class);

	private MethodCallback<R> callback;

	/**
	 * Callback for successful request.
	 * 
	 * @author fmisir
	 * @param <T>
	 *            - type of the Response
	 */
	public interface SuccessFunction<T> {
		void onSuccess(Method method, T response);
	}

	/**
	 * 
	 * @param callback
	 */
	private RestClient(MethodCallback<R> callback) {
		this.callback = callback;
	}

	/**
	 * Builds a RestClient with the given {@code SuccessFunction}.
	 * {@code AbstractMethodCallback} will be used as MethodCallback. Error
	 * Handling will be done in {@code AbstractMethodCallback}.
	 *
	 * Example usage:
	 * 
	 * RestClient.build(new SuccessFunction<List<Person>>() {
	 * 
	 * @Override public void onSuccess(Method method, List<Person> response) {
	 *           // handle response here. } }).personService().getPersons();
	 * 
	 * @param successFunction
	 *            - which will executed when the request was successful
	 * @return an instance of RestClient
	 */
	public static <R> RestClient<R> build(final SuccessFunction<R> successFunction) {
		AbstractMethodCallback<R> callback = new AbstractMethodCallback<R>() {
			@Override
			public void onSuccess(Method method, R response) {
				successFunction.onSuccess(method, response);
			}
		};
		return new RestClient<R>(callback);
	}

	/**
	 * Sets the callback to the given Service.
	 * 
	 * @param service
	 *            - for which the callback should be set
	 * @return the service instance
	 */
	private final <T extends DirectRestService> T setCallback(T service) {
		((CallbackAware) service).setCallback(callback);
		return service;
	}
	
	public EmployeeService getEmployeeService()
	{
		return setCallback(EMPLOYEE_SERVICE);
	}

}
