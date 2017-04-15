package org.deltaa.superrduperr.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import org.deltaa.superrduperr.exception.InvalidRequestException;

/**
 * @author Ashish Gajabi
 * Exception mapper class for custom invalid request exception
 */
@Provider
@Component
public class InvalidRequestExceptionMapper implements ExceptionMapper<InvalidRequestException> {

	@Override
	public Response toResponse(InvalidRequestException e) {
		return Response.status(Status.BAD_REQUEST)
				.type(MediaType.APPLICATION_JSON)
				.entity(e.getErrorMessage())
				.build();
	}

}
