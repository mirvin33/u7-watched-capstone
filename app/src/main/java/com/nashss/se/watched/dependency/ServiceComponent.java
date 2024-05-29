package com.nashss.se.watched.dependency;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.watched.activity.*;
import com.nashss.se.watched.activity.request.DeleteWatchlistRequest;
import com.nashss.se.watched.dynamodb.WatchlistDao;

import com.nashss.se.watched.lambda.AddContentToWatchlistLambda;
import dagger.Component;

import javax.inject.Singleton;


/**
 * Dagger component for providing dependencies such as DAOs and activities.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the WatchlistDao dependency.
     *
     * @return the WatchlistDao instance
     */
    WatchlistDao provideWatchlistDao();

    /**
     * Provides the GetWatchlistActivity dependency.
     *
     * @return the GetWatchlistActivity instance
     */
    GetWatchlistActivity provideGetWatchlistActivity();

    CreateWatchlistActivity provideCreateWatchlistActivity();

    UpdateWatchlistActivity provideUpdateWatchlistActivity();

    AddContentToWatchlistActivity provideAddContentToWatchlistActivity();

    DeleteWatchlistActivity provideDeleteWatchlistActivity();
}

