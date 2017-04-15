package org.deltaa.superrduperr.exception.mapper;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.deltaa.superrduperr.model.ErrorMessage;

/**
 * @author Ashish Gajabi
 * Exception mapper class for resource not found exception
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

	@Context UriInfo uriInfo;
	
	@Override
	public Response toResponse(NotFoundException e) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage("No HTTP resource was found that matches the request URI '"+uriInfo.getRequestUri());
		return Response.status(Status.NOT_FOUND)
				.type(MediaType.APPLICATION_JSON)
				.entity(errorMessage)
				.build();
	}

}
