package com.home911.reviewed.servlets.resources;

import com.google.inject.Inject;
import com.home911.reviewed.model.Comment;
import com.home911.reviewed.model.Summary;
import com.home911.reviewed.service.comment.CommentService;
import com.home911.reviewed.service.summary.SummaryService;
import com.home911.reviewed.service.user.UserService;
import com.home911.reviewed.servlets.model.CommentJson;
import com.home911.reviewed.servlets.model.SummaryJson;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("/summary")
public class SummaryResource extends AbstractBaseResource {
    private static final Logger LOGGER = Logger.getLogger(SummaryResource.class.getCanonicalName());

    @Inject
    public SummaryResource(SummaryService summaryService, CommentService commentService, UserService userService) {
        super(summaryService, commentService, userService);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{sid}")
    public SummaryJson getSummary(@PathParam("sid") String id) {
        Summary summary = summaryService.getSummary(id);
        SummaryJson json = convert(summaryService.getSummary(id));
        json.setCount(commentService.getCommentsCount(summary));
        return json;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{sid}/comments")
    public List<CommentJson> getComments(@PathParam("sid") String sid, @QueryParam("offset") int offset, @QueryParam("limit") int limit) {
        Summary summary = summaryService.getSummary(sid);
        List<Comment> comments = commentService.getComments(summary, offset, limit);
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
    public Response postSummary(SummaryJson summaryJson) {
        Summary summary = convert(summaryJson);

        if (StringUtils.isNotEmpty(summary.getId())) {
            // update
            Summary ori = summaryService.getSummary(summary.getId());
            summary.setRating(ori.getRating());
        }
        String id = summaryService.saveSummary(summary);

        if (id == null) {
            return Response.ok(summaryJson).build();
        } else {
            summaryJson.setId(id);
            return Response.created(getUri(id)).entity(summaryJson).build();
        }
    }
}
