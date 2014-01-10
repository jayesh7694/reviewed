package com.home911.reviewed.model;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;

public enum SummaryType {
    MOVIE, MUSIC, RESTAURANT, STORE, CAR;

    public static class SummaryTypeDeserializer extends JsonDeserializer<SummaryType>
    {
        /**
         * Constructor
         */
        public SummaryTypeDeserializer()
        {
        }

        /** {@inheritDoc} */
        @Override
        public SummaryType deserialize(final JsonParser aParser, final DeserializationContext aContext)
                throws IOException
        {
            return valueOf(aParser.getText().toUpperCase());
        }
    }

    public static class SummaryTypeSerializer extends JsonSerializer<SummaryType>
    {
        /**
         * Constructor
         */
        public SummaryTypeSerializer()
        {
        }

        /** {@inheritDoc} */
        @Override
        public void serialize(SummaryType aValue, JsonGenerator aGenerator, SerializerProvider aProvider)
                throws IOException
        {
            aGenerator.writeString(aValue.name());
        }
    }
}
