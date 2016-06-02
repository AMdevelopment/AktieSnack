package se.amdev.aktiesnackserverweb.web;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import se.amdev.aktiesnackserverdata.model.ThreadData;
import se.amdev.aktiesnackserverweb.model.ThreadWeb;
import se.amdev.aktiesnackserverweb.service.WebService;
import static se.amdev.aktiesnackserverweb.parser.ModelParser.*;

import java.net.URI;
import java.util.Collection;

@Path("/thread")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ThreadWebApi {

	private WebService service;

	@Context
	private UriBuilder uriBuilder;

	@Context
	private UriInfo uriInfo;

	@Context
	private HttpHeaders headers;

	public ThreadWebApi() {
		this.service = new WebService();
	}

	@GET
	public Response getThread(@QueryParam("tn") String threadNumber, @QueryParam("top") String top) {
		if (threadNumber != null) {
			ThreadData threadData = service.findThreadByNumber(threadNumber);
			if (threadData != null) {
				return Response.ok(new ThreadWeb(threadData)).build();
			}
			else {
				return Response.status(Status.BAD_REQUEST).build();
			}
			
		}
		if(top != null){
			Collection<ThreadWeb> threads = parseCollectionThread(service.findLastUpdatedThreads());
			GenericEntity<Collection<ThreadWeb>> entity = new GenericEntity<Collection<ThreadWeb>>(threads){};
			
			return Response.ok(entity).build();
		}
		else{
			Collection<ThreadWeb> threads = parseCollectionThread(service.findAllThreads());
			GenericEntity<Collection<ThreadWeb>> entity = new GenericEntity<Collection<ThreadWeb>>(threads){};
			
			return Response.ok(entity).build();
		}
	}

	@POST
	public Response postThread(ThreadWeb threadWeb) {
		ThreadData threadData = service.save(service.create(threadWeb.getThreadNumber(), threadWeb.getDescription()));

		URI location = uriInfo.getAbsolutePathBuilder().path("hej/").build(threadWeb.getThreadNumber());
		return Response.created(location).entity(new ThreadWeb(threadData)).build();
	}
}