package com.home911.reviewed.servlets.resources;

import com.google.inject.Inject;
import com.home911.reviewed.model.Comment;
import com.home911.reviewed.model.User;
import com.home911.reviewed.service.comment.CommentService;
import com.home911.reviewed.service.summary.SummaryService;
import com.home911.reviewed.service.user.UserService;
import com.home911.reviewed.servlets.model.CommentJson;
import com.home911.reviewed.servlets.model.UserJson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("/user")
public class UserResource extends AbstractBaseResource {
    private static final Logger LOGGER = Logger.getLogger(UserResource.class.getCanonicalName());

    @Inject
    public UserResource(SummaryService summaryService, CommentService commentService, UserService userService) {
        super(summaryService, commentService, userService);
    }

    @GET
     @Consumes(MediaType.APPLICATION_JSON)
     @Produces({MediaType.APPLICATION_JSON})
     @Path("/{uid}")
     public UserJson getUser(@PathParam("uid") String uid) {
        User user = userService.getUser(uid);
        UserJson json = convert(user);
        json.setCount(commentService.getCommentsCount(user));
        return json;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{uid}/comments")
    public List<CommentJson> getComments(@PathParam("uid") String uid,
                                    @QueryParam("offset") int offset, @QueryParam("limit") int limit) {
        User user = userService.getUser(uid);
        List<Comment> comments = commentService.getComments(user, offset, limit);
        List<CommentJson> commentsJson = new ArrayList<CommentJson>(comments.size());
        for (Comment comment : comments) {
            commentsJson.add(convert(comment));
        }

        if (comments.isEmpty()) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        } else {
            return commentsJson;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response postUser(UserJson userJson) {
        User user = convert(userJson);
        userService.saveUser(user);
        return Response.created(getUri(user.getUsername())).entity(userJson).build();
    }
}
