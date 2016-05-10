/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.greenbeansit.jobtracker.server.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import net.greenbeansit.jobtracker.server.rest.services.EmployeeRestServiceImpl;

@Component
public class JerseyConfig extends ResourceConfig
{

	public JerseyConfig()
	{
		packages("net.greenbeansit.jobtracker.server.services");
		register(JacksonJsonProvider.class);

		register(EmployeeRestServiceImpl.class);

		for (Class<?> c : getClasses())
			System.out.println(c.toString());

		System.out.println("JerseyConfig finished");
	}

}
