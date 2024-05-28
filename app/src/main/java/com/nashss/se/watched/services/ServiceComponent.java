package com.nashss.se.watched.services;

import com.nashss.se.watched.activity.GetWatchlistActivity;

import dagger.Component;

import javax.inject.Singleton;


/**
 * Dagger component for providing service dependencies.
 */
@Singleton
@Component(modules = {ServiceModule.class})
public interface ServiceComponent {

    /**
     * Provides the GetWatchlistActivity dependency.
     *
     * @return the GetWatchlistActivity instance
     */
    GetWatchlistActivity provideGetWatchlistActivity();
}

