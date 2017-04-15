package org.deltaa.superrduperr.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.deltaa.superrduperr.model.ErrorMessage;
import org.springframework.stereotype.Component;

/**
 * @author Ashish Gajabi
 * Exception mapper class for all exception types
 */
@Provider
@Component
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage("Error! Please contact system administrator.");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.type(MediaType.APPLICATION_JSON)
				.entity(errorMessage)
				.build();
	}

}
