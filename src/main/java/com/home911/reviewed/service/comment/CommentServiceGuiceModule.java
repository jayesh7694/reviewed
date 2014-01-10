package com.home911.reviewed.service.comment;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class CommentServiceGuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CommentService.class).to(CommentServiceImpl.class).in(Singleton.class);
    }
}
