package com.home911.reviewed;

import com.google.inject.AbstractModule;
import com.home911.reviewed.service.comment.CommentServiceGuiceModule;
import com.home911.reviewed.service.summary.SummaryServiceGuiceModule;
import com.home911.reviewed.service.user.UserServiceGuiceModule;
import com.home911.reviewed.servlets.ReviewedGuiceServletModule;

public class ReviewedResteasyGuiceConfig extends AbstractModule {


    @Override
    protected void configure() {
        install(AppEngineGuiceModule.build().withUserService().withMailService());
        install(new SummaryServiceGuiceModule());
        install(new CommentServiceGuiceModule());
        install(new UserServiceGuiceModule());
        install(new ReviewedGuiceServletModule());
    }
}
