package org.deltaa.superrduperr.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.deltaa.superrduperr.exception.InvalidRequestException;
import org.deltaa.superrduperr.model.ToDoItem;
import org.deltaa.superrduperr.service.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Ashish Gajabi
 * Resource class for /todoitems
 */

@Path("todoitems")
@Produces (MediaType.APPLICATION_JSON)
@Consumes (MediaType.APPLICATION_JSON)
public class ToDoItemResource {
	
	@Autowired
	private ToDoItemService toDoItemService;

	@GET
	public Response getItemList(@QueryParam ("status") final String status,
								@QueryParam ("isdeleted") final Boolean isdeleted,
								@QueryParam ("tag") final String tag,
								@QueryParam ("from") final String fromDate,
								@QueryParam ("to") final String toDate) {
		if(toDate != null && toDate.trim().length() != 8) {
			throw new InvalidRequestException("Invalid dates. Please enter only in 'ddMMyyyy' format e.g. 25072017");
		}
		List<ToDoItem> toDoList = toDoItemService.getAllToDoList(status, isdeleted, tag, fromDate, toDate);	
		if (toDoList != null && toDoList.size() > 0) {
			return Response.ok(toDoList, MediaType.APPLICATION_JSON).build();
		} else {
			throw new NotFoundException();
		}
		
	}
	
	@GET
	@Path ("/{itemId}")
	public Response getItem(@PathParam ("itemId") String itemId) {
		ToDoItem toDoItem = toDoItemService.getItem(itemId);
		if(toDoItem == null) {
			throw new NotFoundException();
		}
		return Response.ok(toDoItem, MediaType.APPLICATION_JSON).build();
	}

	@POST
	public Response addItem(final ToDoItem toDoItem,
							final @Context UriInfo uriInfo) {
		toDoItem.setId(null);
		ToDoItem newToDoItem = toDoItemService.addToDoItem(new ToDoItem ("FirstToDO"));
		if(newToDoItem == null) {
			throw new InternalServerErrorException();
		}
		final URI loc = uriInfo.getAbsolutePathBuilder().path("" + newToDoItem.getId()).build();
		return Response.created(loc).entity(newToDoItem).build();
	}

	@DELETE
	@Path("/{itemId}")
	@Produces (MediaType.TEXT_PLAIN)
	public Response markItemAsDelete(@PathParam ("itemId") String itemId) {
		toDoItemService.updateItemAsDelete(itemId);		
		return Response.status(Status.OK).header("RESOURCE_UPDATED", "TRUE")
				.entity("Resource marked as Deleted successfully!").build();
	}	

	@PUT
	@Path("/{itemId}")
	public Response updateToDoItem(@PathParam ("itemId") String itemId,
										   final ToDoItem toDoItem ) {
		ToDoItem updatedToDoItem = toDoItemService.updateToDoItem(toDoItem, itemId);		
		return Response.ok(updatedToDoItem).build();
	}
	
	@PUT
	@Path("/{itemId}/completed")
	public Response updateToDoStatusAsComplete(@PathParam ("itemId") String itemId) {
		ToDoItem toDoItem = toDoItemService.updateStatusAsComplete(itemId);		
		return Response.ok(toDoItem).build();
	}
	

	@PUT
	@Path("/{itemId}/restore")
	public Response restoreItem(@PathParam ("itemId") String itemId) {
		ToDoItem toDoItem = toDoItemService.restoreItem(itemId);		
		return Response.ok(toDoItem).build();
	}

	@PUT
	@Path("/{itemId}/tag/{tagValue}")
	public Response addTagtoItem(@PathParam ("itemId") String itemId,
										@PathParam ("tagValue") String tagValue) {
		if(tagValue == null || tagValue.trim().length() == 0) {
			throw new InvalidRequestException("Invalid tags");
		}
		ToDoItem toDoItem = toDoItemService.addTagtoItem(itemId, tagValue);		
		return Response.ok(toDoItem).build();
	}

	@PUT
	@Path("/{itemId}/reminder/{ddmmyyyy}")
	public Response addReminderToItem(@PathParam ("itemId") String itemId,
										@PathParam ("ddmmyyyy") String reminderDate) {
		if(reminderDate == null || reminderDate.trim().length() != 8) {
			throw new InvalidRequestException("Invalid reminder date. Please enter only in 'ddMMyyyy' format e.g. /25072017");
		}
		ToDoItem toDoItem = toDoItemService.addReminderToItem(itemId, reminderDate);		
		return Response.ok(toDoItem).build();
	}

}
