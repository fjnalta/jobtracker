package net.greenbeansit.timer.client.utils.rest;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.user.client.Window;

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
		  //TODO Error handling have to be implemented here.
		  Window.alert("Erorr Happend here");
	  }

	  @Override
	  public abstract void onSuccess(Method method, T response);




}
