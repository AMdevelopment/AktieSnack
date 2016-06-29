package se.amdev.aktiesnackserverweb.web;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import se.amdev.aktiesnackserverdata.model.InquiryData;
import se.amdev.aktiesnackserverweb.service.WebService;

@Path("/inquiry")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public class RegisterWebApi {

	private WebService service;

	@Context
	private UriBuilder uriBuilder;

	@Context
	private UriInfo uriInfo;

	@Context
	private HttpHeaders headers;

	public RegisterWebApi() {
		this.service = new WebService();
	}

	@GET
	public Response getEmail() {
		Collection<InquiryData> email = service.findAllRegistredEmail();
		if (email.isEmpty()) {
			return Response.status(Status.NO_CONTENT).build();
		}

		GenericEntity<Collection<InquiryData>> entity = new GenericEntity<Collection<InquiryData>>(email)
		{
		};

		return Response.ok(entity).build();
	}

	@POST
	public Response postEmail(InquiryData email) {
		
		service.registerEmail(email);

		return Response.ok("OK").build();
	}
}
