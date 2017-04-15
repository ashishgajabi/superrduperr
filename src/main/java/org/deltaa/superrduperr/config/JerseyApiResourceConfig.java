package org.deltaa.superrduperr.config;

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * @author Ashish Gajabi Jersey configuration class with package containing all
 *         resources
 */
@Component
@ApplicationPath("/superrduperr")
public class JerseyApiResourceConfig extends ResourceConfig {

	public JerseyApiResourceConfig(@Context ServletContext context) {
		packages("org.deltaa.superrduperr.resource");
		packages("org.deltaa.superrduperr.exception.mapper");
	}

}
