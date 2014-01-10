package com.home911.reviewed.servlets.model;

import com.home911.reviewed.model.Rating;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

import java.util.HashSet;
import java.util.Set;

public class CommentJson {
    //@JsonProperty("id")
    private String id;
    //@JsonProperty("text")
    private String text;
    //@JsonProperty("user")
    private String user;
    //@JsonProperty("rating")
    //@JsonDeserialize(using = Rating.RatingDeserializer.class)
    //@JsonSerialize(using = Rating.RatingSerializer.class)
    private Rating rating;
    //@JsonProperty("likes")
    private Set<String> likes = new HashSet<String>();
    //@JsonProperty("created")
    //@JsonDeserialize(using = DateTimeJsonUtils.DateTimeDeserializer.class)
    //@JsonSerialize(using = DateTimeJsonUtils.DateTimeSerializer.class)
    private DateTime created;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Set<String> getLikes() {
        return likes;
    }

    public void setLikes(Set<String> likes) {
        this.likes = likes;
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

        CommentJson that = (CommentJson) o;

        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (likes != null ? !likes.equals(that.likes) : that.likes != null) return false;
        if (rating != that.rating) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
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
                .append("rating", rating)
                .append("likes", likes)
                .append("created", created)
                .toString();
    }
}
