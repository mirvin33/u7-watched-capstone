package com.nashss.se.watched.dependency;

import com.nashss.se.watched.activity.AddContentToWatchlistActivity;
import com.nashss.se.watched.activity.CreateWatchlistActivity;
import com.nashss.se.watched.activity.DeleteWatchlistActivity;
import com.nashss.se.watched.activity.GetWatchlistActivity;
import com.nashss.se.watched.activity.GetWatchlistContentActivity;
import com.nashss.se.watched.activity.GetWatchlistsForUserActivity;
import com.nashss.se.watched.activity.UpdateWatchlistActivity;
import com.nashss.se.watched.dynamodb.WatchlistDao;

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

    /**
     * Provides the CreateWatchlistActivity dependency.
     *
     * @return the CreateWatchlistActivity instance
     */
    CreateWatchlistActivity provideCreateWatchlistActivity();

    /**
     * Provides the UpdateWatchlistActivity dependency.
     *
     * @return the UpdateWatchlistActivity instance
     */
    UpdateWatchlistActivity provideUpdateWatchlistActivity();

    /**
     * Provides the AddContentToWatchlistActivity dependency.
     *
     * @return the AddContentToWatchlistActivity instance
     */
    AddContentToWatchlistActivity provideAddContentToWatchlistActivity();

    /**
     * Provides the DeleteWatchlistActivity dependency.
     *
     * @return the DeleteWatchlistActivity instance
     */
    DeleteWatchlistActivity provideDeleteWatchlistActivity();

    /**
     * Provides the GetWatchlistContentActivity dependency.
     *
     * @return the GetWatchlistContentActivity instance
     */
    GetWatchlistContentActivity provideGetWatchlistContentActivity();

    /**
     * Provides the GetWatchlistsForUserActivity dependency.
     *
     * @return the GetWatchlistsForUserActivity instance
     */
    GetWatchlistsForUserActivity provideGetWatchlistsForUserActivity();
}

