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
import se.amdev.aktiesnackserverweb.model.PostWeb;
import se.amdev.aktiesnackserverweb.service.WebService;
import static se.amdev.aktiesnackserverweb.parser.ModelParser.*;

import java.util.Collection;

@Path("/post")
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
	public Response getPost(@PathParam("pn") String postNumber){
		if (postNumber != null) {
			PostData postData = service.findPostByNumber(postNumber);
			if (postData != null) {
				return Response.ok(new PostWeb(postData)).build();
			}
			else {
				return Response.status(Status.BAD_REQUEST).build();
			}
		}else{
			Collection<PostWeb> posts = parseCollectionPost(service.findAllPosts());
			GenericEntity<Collection<PostWeb>> entity = new GenericEntity<Collection<PostWeb>>(posts){};
			
			return Response.ok(entity).build();
		}
	}
	
	@POST
	public Response postPost(PostWeb postWeb){
		PostData postData = new PostData(parseUser(postWeb.getUser()), parseThread(postWeb.getThread()), postWeb.getText());
		
		return Response.ok(new PostWeb(service.addPost(postData))).build();
	}
}