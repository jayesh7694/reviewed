package com.home911.reviewed.servlets.resources;

import com.google.inject.Inject;
import com.home911.reviewed.model.Summary;
import com.home911.reviewed.service.comment.CommentService;
import com.home911.reviewed.service.summary.SummaryService;
import com.home911.reviewed.service.user.UserService;
import com.home911.reviewed.servlets.model.SummaryJson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("/summaries")
public class SummariesResource extends AbstractBaseResource {
    private static final Logger LOGGER = Logger.getLogger(SummariesResource.class.getCanonicalName());

    @Inject
    public SummariesResource(SummaryService summaryService, CommentService commentService, UserService userService) {
        super(summaryService, commentService, userService);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<SummaryJson> getSummaries(@QueryParam("offset") int offset, @QueryParam("limit") int limit) {
        List<Summary> summaries = summaryService.getSummaries(offset, limit);
        List<SummaryJson> summariesJson = new ArrayList<SummaryJson>(summaries.size());
        for (Summary summary : summaries) {
            summariesJson.add(convert(summary));
        }

        if (summaries.isEmpty()) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        } else {
            return summariesJson;
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/s")
    public List<SummaryJson> searchSummaries(@QueryParam("termField") String field, @QueryParam("termValue") String value,
                                             @QueryParam("offset") int offset, @QueryParam("limit") int limit) {
        List<Summary> summaries = summaryService.searchSummaries(field, value, offset, limit);
        List<SummaryJson> summariesJson = new ArrayList<SummaryJson>(summaries.size());
        for (Summary summary : summaries) {
            summariesJson.add(convert(summary));
        }

        if (summaries.isEmpty()) {
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        } else {
            return summariesJson;
        }
    }
}
