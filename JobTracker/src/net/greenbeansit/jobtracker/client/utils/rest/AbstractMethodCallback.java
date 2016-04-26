package net.greenbeansit.jobtracker.client.utils.rest;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.user.client.Window;

import net.greenbeansit.jobtracker.shared.exceptions.InvalidInput;

/**
 * Abstract implementation of {@code MethodCallback}. The onFailure Method is overriden, so 
 * that error handling can made in a single place. 
 *  
 * @author Fethullah Misir
 * @param <T>	
 */
public abstract class AbstractMethodCallback<T> implements MethodCallback<T> {


	  @Override
	  public void onFailure(Method method, Throwable exception) {
		  if(exception.getClass().equals(new InvalidInput().getClass())) {
			  Window.alert("Invalid input used on shared class");
		  }
		  Window.alert("Erorr Happend here");
	  }

	  @Override
	  public abstract void onSuccess(Method method, T response);




}
