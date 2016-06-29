package se.amdev.aktiesnackserverweb.web;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import se.amdev.aktiesnackserverdata.model.PostData;
import se.amdev.aktiesnackserverdata.model.ThreadData;
import se.amdev.aktiesnackserverdata.model.UserData;
import se.amdev.aktiesnackserverweb.model.PostWeb;
import se.amdev.aktiesnackserverweb.service.WebService;
import static se.amdev.aktiesnackserverweb.parser.ModelParser.*;

import java.util.Collection;

@Path("/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostWebApi {

	private WebService service;

	@Context
	private UriBuilder uriBuilder;

	@Context
	private UriInfo uriInfo;

	@Context
	private HttpHeaders headers;

	public PostWebApi() {
		this.service = new WebService();
	}

	@GET
	public Response getPost(@QueryParam("pn") String postNumber, @QueryParam("tn") String threadName) {
		if (postNumber != null) {
			PostData postData = service.findPostByNumber(postNumber);
			if (postData != null) {
				return Response.ok(new PostWeb(postData)).build();
			}
			else {
				return Response.status(Status.NO_CONTENT).build();
			}
		}
		if (threadName != null) {
			ThreadData threadData = service.findThreadByName(threadName);
			
			Collection<PostWeb> posts = parseCollectionPost(service.findAllPostsByThreadId(threadData.getId()));
			if (posts.isEmpty()) {
				return Response.status(Status.NO_CONTENT).build();
			}

			GenericEntity<Collection<PostWeb>> entity = new GenericEntity<Collection<PostWeb>>(posts)
			{
			};

			return Response.ok(entity).build();
		}
		else {
			Collection<PostWeb> posts = parseCollectionPost(service.findAllPosts());
			if (posts.isEmpty()) {
				return Response.status(Status.NO_CONTENT).build();
			}

			GenericEntity<Collection<PostWeb>> entity = new GenericEntity<Collection<PostWeb>>(posts)
			{
			};

			return Response.ok(entity).build();
		}
	}

	@POST
	public Response postPost(@QueryParam("tn") String threadName, @QueryParam("user") String username, PostWeb postWeb) {
		ThreadData threadData = service.findThreadByName(threadName);
		UserData userData = service.findUserByUsername(username);
		PostData postData = new PostData(userData, threadData, postWeb.getText());
//		postData.setThread(threadData);
		service.addPost(postData);

		return Response.ok(new PostWeb(postData)).build();
	}
}