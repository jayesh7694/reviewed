package com.home911.reviewed.service.comment;

import com.home911.reviewed.model.Comment;
import com.home911.reviewed.model.Summary;
import com.home911.reviewed.model.User;

import java.util.List;

public interface CommentService {
    public int getCommentsCount(Summary summary);
    public List<Comment> getComments(Summary summary, int offset, int limit);
    public List<Comment> getComments(User user, int offset, int limit);
    public Comment getComment(String id);
    public int getCommentsCount(User user);
    public void likeComment(String commentId);
    public String saveComment(Comment comment);
}
