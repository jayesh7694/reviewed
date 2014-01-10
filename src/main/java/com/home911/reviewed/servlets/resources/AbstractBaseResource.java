package com.home911.reviewed.servlets.resources;

import com.home911.reviewed.model.Comment;
import com.home911.reviewed.model.Summary;
import com.home911.reviewed.model.User;
import com.home911.reviewed.service.comment.CommentService;
import com.home911.reviewed.service.summary.SummaryService;
import com.home911.reviewed.service.user.UserService;
import com.home911.reviewed.servlets.model.CommentJson;
import com.home911.reviewed.servlets.model.SummaryJson;
import com.home911.reviewed.servlets.model.UserJson;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

public class AbstractBaseResource {
    protected final UserService userService;
    protected final SummaryService summaryService;
    protected final CommentService commentService;

    protected AbstractBaseResource(SummaryService summaryService, CommentService commentService,
                                   UserService userService) {
        this.summaryService = summaryService;
        this.commentService = commentService;
        this.userService = userService;
    }

    protected Summary convert(SummaryJson summaryJson) {
        return new Summary(summaryJson.getId(), summaryJson.getName(), summaryJson.getType(),
                summaryJson.getDescription(), summaryJson.getWebSite(), summaryJson.getRating());
    }

    protected Comment convert(CommentJson commentJson) {
        Comment comment = new Comment();
        comment.setId(commentJson.getId());
        comment.setText(commentJson.getText());
        comment.setRating(commentJson.getRating());
        return comment;
    }

    protected CommentJson convert(Comment comment) {
        CommentJson json = new CommentJson();
        json.setId(comment.getId());
        json.setRating(comment.getRating());
        json.setText(comment.getText());
        json.setUser(comment.getUser().getName());
        json.setLikes(comment.getLikes());
        json.setCreated(comment.getCreated());
        return json;
    }

    protected SummaryJson convert(Summary summary) {
        SummaryJson json = new SummaryJson();
        json.setId(summary.getId());
        json.setWebSite(summary.getWebSite());
        json.setName(summary.getName());
        json.setDescription(summary.getDescription());
        json.setType(summary.getType());
        return json;
    }

    protected User convert(UserJson userJson) {
        User user = new User();
        user.setEmail(userJson.getEmail());
        user.setUsername(userJson.getUsername());
        return user;
    }

    protected UserJson convert(User user) {
        UserJson json = new UserJson();
        json.setEmail(user.getEmail());
        json.setUsername(user.getUsername());
        return json;
    }

    protected URI getUri(String id) {
        try {
            return new URI(id);
        } catch (URISyntaxException e) {
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
        }
    }
}
