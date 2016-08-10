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
import se.amdev.stockdownloader.StockDownloaderMain;

import static se.amdev.aktiesnackserverweb.parser.ModelParser.*;

import java.net.URI;
import java.util.Collection;

@Path("/threads")
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
	public Response getThread(@QueryParam("tn") String threadName, @QueryParam("top") String top) {
		if (threadName != null) {
			ThreadData threadData = service.findThreadByName(threadName);
			if (threadData != null) {
				return Response.ok(new ThreadWeb(threadData)).build();
			}
			else {
				return Response.status(Status.NO_CONTENT).build();
			}

		}
		if (top != null) {
			Collection<ThreadWeb> threads = parseCollectionThread(service.findLastUpdatedThreads());
			if (threads.isEmpty()) {
				return Response.status(Status.NO_CONTENT).build();
			}

			GenericEntity<Collection<ThreadWeb>> entity = new GenericEntity<Collection<ThreadWeb>>(threads)
			{
			};

			return Response.ok(entity).build();
		}
		else {
			Collection<ThreadWeb> threads = parseCollectionThread(service.findAllThreads());
			if (threads.isEmpty()) {
				return Response.status(Status.NO_CONTENT).build();
			}

			GenericEntity<Collection<ThreadWeb>> entity = new GenericEntity<Collection<ThreadWeb>>(threads)
			{
			};

			return Response.ok(entity).build();
		}
	}

	@POST
	public Response postThread(ThreadWeb threadWeb) {
		ThreadData threadData = service.save(service.create(threadWeb.getThreadName(), threadWeb.getDescription(), threadWeb.getCurrency()));

		URI location = uriInfo.getAbsolutePathBuilder().path("hej/").build(threadWeb.getThreadName());
		return Response.created(location).entity(new ThreadWeb(threadData)).build();
	}

	@POST
	@Path("/add")
	public Response postit() {

		StockDownloaderMain.nasdaqCompanyUpdate();
//		StockDownloaderMain.aktietorgetCompanyUpdate();

		return Response.created(null).build();
	}
}
