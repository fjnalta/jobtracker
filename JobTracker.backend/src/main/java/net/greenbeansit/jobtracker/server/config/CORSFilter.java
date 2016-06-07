package net.greenbeansit.jobtracker.server.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * {@link Filter} to add cache control headers for GWT generated files to ensure
 * that the correct files get cached.
 * 
 * @author See Wah Cheng
 */
@Component
public class CORSFilter implements Filter
{
	public void destroy()
	{
	}

	public void init(FilterConfig config) throws ServletException
	{
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException
	{
		// ((HttpServletResponse) response).addHeader("Cache-control",
		// "no-cache");
		((HttpServletResponse) response)
				.addHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse) response).addHeader(
				"Access-Control-Allow-Methods",
				"POST, GET, UPDATE, OPTIONS, DELETE");
		((HttpServletResponse) response).addHeader(
				"Access-Control-Allow-Headers",
				"X-HTTP-Method-Override, Origin, X-Requested-With, Content-Type, Accept");
		filterChain.doFilter(request, response);
	}
}