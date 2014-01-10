package com.home911.reviewed.servlets;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.googlecode.objectify.ObjectifyService;
import com.home911.reviewed.ReviewedExceptionMapper;
import com.home911.reviewed.servlets.resources.CommentResource;
import com.home911.reviewed.servlets.resources.SummariesResource;
import com.home911.reviewed.servlets.resources.SummaryResource;
import com.home911.reviewed.servlets.resources.UserResource;
import com.home911.reviewed.utils.GsonMessageBodyHandler;
import com.home911.reviewed.utils.UTCReadableInstantTranslatorFactory;


public class ReviewedGuiceServletModule extends AbstractModule {

    @Override
    protected void configure() {
        ObjectifyService.factory().getTranslators().add(new UTCReadableInstantTranslatorFactory());

        // Exception mapping binding
        bind(ReviewedExceptionMapper.AllExceptionMapper.class).in(Singleton.class);
        bind(ReviewedExceptionMapper.WebApplicationExceptionMapper.class).in(Singleton.class);

        bind(GsonMessageBodyHandler.class).in(Singleton.class);

        // Resource mapping binding
        bind(UserResource.class).in(Singleton.class);
        bind(SummariesResource.class).in(Singleton.class);
        bind(SummaryResource.class).in(Singleton.class);
        bind(CommentResource.class).in(Singleton.class);
    }
}
