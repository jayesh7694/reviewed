package com.home911.reviewed.service.comment;

import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;
import com.home911.reviewed.model.Comment;
import com.home911.reviewed.model.Summary;
import com.home911.reviewed.model.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class CommentServiceImpl implements CommentService {
    private static final Logger LOGGER = Logger.getLogger(CommentServiceImpl.class.getCanonicalName());

    public CommentServiceImpl() {
        ObjectifyService.register(Comment.class);
    }

    @Override
    public Comment getComment(String id) {
        LOGGER.info("Getting comment for id:[" + id + "]");
        Comment comment = ofy().load().type(Comment.class).id(id).now();
        if (comment == null) {
            throw new NotFoundException();
        }
        return comment;
    }


    @Override
    public int getCommentsCount(Summary summary) {
        return ofy().load().type(Comment.class).filter("summary", summary).count();
    }

    @Override
    public List<Comment> getComments(Summary summary, int offset, int limit) {
        LOGGER.info("Getting comments for summary[" + summary + "]");
        List<Comment> comments = new ArrayList<Comment>();
        Query<Comment> query = ofy().load().type(Comment.class).filter("summary", summary).offset(offset).limit(limit);
        QueryResultIterator<Comment> iterator = query.iterator();
        while (iterator.hasNext()) {
            comments.add(iterator.next());
        }

        return comments;
    }

    @Override
    public List<Comment> getComments(User user, int offset, int limit) {
        LOGGER.info("Getting comments for user[" + user + "]");
        List<Comment> comments = new ArrayList<Comment>();
        Query<Comment> query = ofy().load().type(Comment.class).filter("user", user).offset(offset).limit(limit);
        QueryResultIterator<Comment> iterator = query.iterator();
        while (iterator.hasNext()) {
            comments.add(iterator.next());
        }

        return comments;
    }

    @Override
    public int getCommentsCount(User user) {
        return ofy().load().type(Comment.class).filter("user", user).count();
    }

    @Override
    public void likeComment(String commentId) {

    }

    @Override
    public String saveComment(Comment comment) {
        LOGGER.info("Saving comment: " + comment);
        String id = null;
        if (StringUtils.isEmpty(comment.getId()))
        {
            id = String.valueOf(System.currentTimeMillis());
            comment.setId(id);
        }
        ofy().save().entity(comment).now();
        return id;
    }
}
