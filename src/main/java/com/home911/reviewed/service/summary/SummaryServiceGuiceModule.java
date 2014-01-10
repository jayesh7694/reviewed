package com.home911.reviewed.service.summary;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class SummaryServiceGuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SummaryService.class).to(SummaryServiceImpl.class).in(Singleton.class);
    }
}
