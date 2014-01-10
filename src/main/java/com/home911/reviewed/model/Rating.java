package com.home911.reviewed.model;

import com.google.gson.*;

import java.lang.reflect.Type;

public enum Rating {
    POINT_5(0.5), ONE(1.0), ONE_POINT_FIVE(1.5), TWO(2.0), TWO_POINT_FIVE(2.5), THREE(3.0), THREE_POINT_FIVE(3.5), FOUR(4.0), FOUR_POINT_FIVE(4.5), FIVE(5.0);

    private final double value;

    private Rating(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public static class RatingDeserializer implements JsonDeserializer<Rating> {
        public Rating deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return valueOf(json.getAsJsonPrimitive().getAsString());
        }
    }

    public static class RatingSerializer implements JsonSerializer<Rating> {
        public JsonElement serialize(Rating src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }
    }
}
