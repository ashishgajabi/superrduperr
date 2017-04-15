package org.deltaa.superrduperr.resource;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.deltaa.superrduperr.model.Resourcelink;

/**
 * @author Ashish Gajabi
 * Root resource to give basic information about API
 *
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class RootResource {

	@GET
	public Response getDetails(@Context UriInfo uriInfo) {

		String rootUrl = uriInfo.getAbsolutePathBuilder().path(ToDoItemResource.class).build().toString();
		String itemUrl = rootUrl + "/{itemid}";
		String itemExampleUrl = rootUrl + "/1";
		Resourcelink rootLink = new Resourcelink(rootUrl, "List all TODO Items", rootUrl);
		Resourcelink toDoItemLink = new Resourcelink(itemUrl, "Get Particular TODO Item", itemExampleUrl);

		Set<Resourcelink> allLinks = new HashSet<>();
		allLinks.add(rootLink);
		allLinks.add(toDoItemLink);
		//TODO More links needs to be added
		return Response.ok(allLinks).build();

	}

}
