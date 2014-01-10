package com.home911.reviewed;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReviewedExceptionMapper {
    private static final Logger LOGGER = Logger.getLogger(ReviewedExceptionMapper.class.getCanonicalName());

    @Provider
    public static class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException>
    {
        @Override
        public Response toResponse(final WebApplicationException exception)
        {
            return Response.status(exception.getResponse().getStatus()).entity(exception.getResponse().getEntity()).build();
        }
    }

    @Provider
    public static class AllExceptionMapper implements ExceptionMapper<Exception>
    {
        @Override
        public Response toResponse(final Exception exception)
        {
            ReviewedExceptionMapper.LOGGER.log(Level.WARNING, "Internal error.", exception);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        }
    }
}
