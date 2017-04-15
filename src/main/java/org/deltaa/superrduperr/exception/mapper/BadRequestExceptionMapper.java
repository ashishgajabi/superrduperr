package org.deltaa.superrduperr.exception.mapper;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.deltaa.superrduperr.model.ErrorMessage;
import org.springframework.stereotype.Component;

/**
 * @author Ashish Gajabi
 * Exception mapper class for bad request
 */
@Provider
@Component
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

	@Override
	public Response toResponse(BadRequestException e) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage("Bad Request");
		return Response.status(Status.BAD_REQUEST)
				.type(MediaType.APPLICATION_JSON)
				.entity(errorMessage)
				.build();
	}

}
