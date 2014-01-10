package com.home911.reviewed.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Summary {
    @Id
    private String id;
    @Index
    private String name;
    @Index
    private SummaryType type;
    private String description;
    private String webSite;
    private Rating rating;

    public Summary(String id, String name, SummaryType type, String description, String webSite, Rating rating) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.webSite = webSite;
        this.rating = rating;
    }

    public Summary() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SummaryType getType() {
        return type;
    }

    public void setType(SummaryType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Summary that = (Summary) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != that.type) return false;
        if (webSite != null ? !webSite.equals(that.webSite) : that.webSite != null) return false;
        if (rating != null ? !rating.equals(that.webSite) : that.rating != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (webSite != null ? webSite.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("type", type)
                .append("description", description)
                .append("webSite", webSite)
                .append("rating", rating)
                .toString();
    }
}
