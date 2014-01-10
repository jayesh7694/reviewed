package com.home911.reviewed.model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Comment {
    @Id
    private String id;
    private String text;
    @Index
    private Key<User> user;
    @Index
    private Key<Summary> summary;
    private Rating rating;
    private Set<String> likes;
    private DateTime created;

    public Comment(String id, String text, User user, Summary summary, Rating rating, DateTime created) {
        this();
        this.id = id;
        this.text = text;
        this.rating = rating;
        this.user = Key.create(User.class, user.getUsername());
        this.summary = Key.create(Summary.class, summary.getId());
        this.created = created;
    }

    public Comment() {
        this.created = new DateTime(DateTimeZone.UTC);
        this.likes = new HashSet<String>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Key<User> getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = Key.create(User.class, user.getUsername());
    }

    public Key<Summary> getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = Key.create(Summary.class, summary.getId());
    }

    public Set<String> getLikes() {
        return likes;
    }

    public void setLikes(Set<String> likes) {
        this.likes = likes;
    }

    public void addLike(String username) {
        this.likes.add(username);
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (created != null ? !created.equals(comment.created) : comment.created != null) return false;
        if (id != null ? !id.equals(comment.id) : comment.id != null) return false;
        if (likes != null ? !likes.equals(comment.likes) : comment.likes != null) return false;
        if (rating != comment.rating) return false;
        if (summary != null ? !summary.equals(comment.summary) : comment.summary != null) return false;
        if (text != null ? !text.equals(comment.text) : comment.text != null) return false;
        if (user != null ? !user.equals(comment.user) : comment.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (likes != null ? likes.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("text", text)
                .append("user", user)
                .append("summary", summary)
                .append("rating", rating)
                .append("likes", likes)
                .append("created", created)
                .toString();
    }
}
