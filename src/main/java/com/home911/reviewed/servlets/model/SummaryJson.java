package com.home911.reviewed.servlets.model;

import com.home911.reviewed.model.Rating;
import com.home911.reviewed.model.SummaryType;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SummaryJson {
    //@JsonProperty("id")
    private String id;
    //@JsonProperty("name")
    private String name;
    //@JsonProperty("type")
    //@JsonDeserialize(using = SummaryType.SummaryTypeDeserializer.class)
    //@JsonSerialize(using = SummaryType.SummaryTypeSerializer.class)
    private SummaryType type;
    //@JsonProperty("rating")
    //@JsonDeserialize(using = Rating.RatingDeserializer.class)
    //@JsonSerialize(using = Rating.RatingSerializer.class)
    private Rating rating;
    //@JsonProperty("desc")
    private String description;
    //@JsonProperty("webSite")
    private String webSite;
    //@JsonProperty("count")
    private int count;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

        SummaryJson that = (SummaryJson) o;

        if (count != that.count) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (rating != that.rating) return false;
        if (type != that.type) return false;
        if (webSite != null ? !webSite.equals(that.webSite) : that.webSite != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (webSite != null ? webSite.hashCode() : 0);
        result = 31 * result + count;
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("type", type)
                .append("rating", rating)
                .append("description", description)
                .append("webSite", webSite)
                .append("count", count)
                .toString();
    }
}
