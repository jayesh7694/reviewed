package com.home911.reviewed.servlets.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserJson {
    //@JsonProperty("username")
    private String username;
    //@JsonProperty("email")
    private String email;
    //@JsonProperty("count")
    private int count;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserJson userJson = (UserJson) o;

        if (count != userJson.count) return false;
        if (email != null ? !email.equals(userJson.email) : userJson.email != null) return false;
        if (username != null ? !username.equals(userJson.username) : userJson.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + count;
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("email", email)
                .append("count", count)
                .toString();
    }
}
