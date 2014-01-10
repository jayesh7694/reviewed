package com.home911.reviewed.service.user;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.home911.reviewed.service.summary.SummaryService;
import com.home911.reviewed.service.summary.SummaryServiceImpl;

public class UserServiceGuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserService.class).to(UserServiceImpl.class).in(Singleton.class);
    }
}
