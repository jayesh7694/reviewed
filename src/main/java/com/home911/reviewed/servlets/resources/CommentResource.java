package com.home911.reviewed.servlets.resources;

import com.google.appengine.api.mail.MailService;
import com.google.inject.Inject;
import com.home911.reviewed.model.Comment;
import com.home911.reviewed.model.Summary;
import com.home911.reviewed.model.User;
import com.home911.reviewed.service.comment.CommentService;
import com.home911.reviewed.service.summary.SummaryService;
import com.home911.reviewed.service.user.UserService;
import com.home911.reviewed.servlets.model.CommentJson;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/summary/{sid}/comment")
public class CommentResource extends AbstractBaseResource {
    private static final Logger LOGGER = Logger.getLogger(CommentResource.class.getCanonicalName());

    private final MailService mailService;

    @Inject
    public CommentResource(SummaryService summaryService, CommentService commentService, UserService userService,
                           MailService mailService) {
        super(summaryService, commentService, userService);
        this.mailService = mailService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response postComment(@PathParam("sid") String sid, CommentJson commentJson) {
        Summary summary = summaryService.getSummary(sid);
        User user = userService.getUser(commentJson.getUser());
        Comment comment = convert(commentJson);
        comment.setSummary(summary);
        comment.setUser(user);

        if (StringUtils.isNotEmpty(comment.getId())) {
            // update
            Comment ori = commentService.getComment(comment.getId());
            comment.setLikes(ori.getLikes());
            commentJson.setLikes(ori.getLikes());
            commentJson.setCreated(ori.getCreated());
        } else {
            commentJson.setCreated(comment.getCreated());
        }
        String id = commentService.saveComment(comment);

        if (id == null) {
            return Response.ok(commentJson).build();
        } else {
            commentJson.setId(id);
            return Response.created(getUri(id)).entity(commentJson).build();
        }
    }

    @PUT
    @Path("/{cid}/like")
    public Response like(@PathParam("sid") String sid, @PathParam("cid") String cid,
                         @QueryParam("username") String username) {
        // TODO user management...
        Comment comment = commentService.getComment(cid);
        User user = userService.getUser(comment.getUser().getName());
        comment.addLike(username);
        commentService.saveComment(comment);

        try {
            mailService.send(getMessage(user, username, comment));
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Unable to send email...", e);
        }

        return Response.ok().build();
    }

    private MailService.Message getMessage(User user, String username, Comment comment) {
        MailService.Message msg = new MailService.Message();
        msg.setSender("do.not.reply@reviewed.home911.com");
        msg.setTo(user.getEmail());
        msg.setSubject(username + " like your comment...");
        StringBuilder body = new StringBuilder();
        body.append("http://localhost:8080/rest/summary/")
            .append(comment.getSummary().getName())
            .append("/comment/")
            .append(comment.getId())
            .append("\n\n")
            .append("Thanks for using Reviewed!\n");

        msg.setTextBody(body.toString());
        return msg;
    }
}
