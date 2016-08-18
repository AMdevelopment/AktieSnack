package se.amdev.aktiesnackserverweb.web;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import se.amdev.aktiesnackserverdata.model.UserData;
import se.amdev.aktiesnackserverweb.model.UserWeb;
import se.amdev.aktiesnackserverweb.parser.MD5encryption;
import se.amdev.aktiesnackserverweb.service.WebService;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserWebApi {

	private WebService service;

	@Context
	private UriBuilder uriBuilder;

	@Context
	private UriInfo uriInfo;

	@Context
	private HttpHeaders headers;

	private static HashMap<String, UUID> keys = new HashMap<>();

	public UserWebApi() {
		this.service = new WebService();
	}

	@GET
	public Response getUserByUsername(@HeaderParam("username") String username, @HeaderParam("key") String key) {
		UUID keyn = keys.get(username);

		if (keyn.toString().equals(key)) {
			UUID uuid = UUID.randomUUID();
			UserData userData = service.findUserByUsername(username);
			if (userData != null) {
				keys.put(username, uuid);
				return Response.ok(new UserWeb(userData)).header("key", uuid).build();
			}
		}
		else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return null;
	}

	@POST
	public Response postUser(UserWeb userWeb) {
		UserData userData = null;
		try {
			userData = service.save(service.create(userWeb.getUsername(), userWeb.getEmail(), userWeb.getFirstName(), userWeb.getLastName(), MD5encryption.encrypt(userWeb.getPassword())));
		}
		catch (NoSuchAlgorithmException e) {
			Response.status(Status.BAD_REQUEST).build();
		}

		URI location = uriInfo.getAbsolutePathBuilder().path("hej/").build(userWeb.getUsername());
		return Response.created(location).entity(new UserWeb(userData)).build();
	}

	@POST
	@Path("/login")
	public Response login(UserWeb userWeb) {
		UserData user = service.findUserByUsername(userWeb.getUsername());
		String password = null;
		try {
			password = MD5encryption.encrypt(userWeb.getPassword());
		}
		catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (password.equals(user.getPassword())) {
			return Response.ok(new UserWeb(user)).build();
		}
		else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	public Response changePassword(UserWeb userWeb){
		UserData userData = service.findUserByUsername(userWeb.getUsername());
		String newPassword;
		try {
			newPassword = MD5encryption.encrypt(userWeb.getPassword());
		}
		catch (NoSuchAlgorithmException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		userData.setPassword(newPassword);
		service.save(userData);
		
		return Response.ok().build();
	}
}
