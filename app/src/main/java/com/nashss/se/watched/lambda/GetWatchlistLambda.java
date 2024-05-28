package com.nashss.se.watched.lambda;

import com.nashss.se.watched.activity.GetWatchlistActivity;
import com.nashss.se.watched.activity.request.GetWatchlistRequest;
import com.nashss.se.watched.activity.results.GetWatchlistResult;
import com.nashss.se.watched.services.DaggerServiceComponent;
import com.nashss.se.watched.services.ServiceComponent;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

/**
 * AWS Lambda function handler for getting a watchlist.
 */
public class GetWatchlistLambda implements RequestHandler<Map<String, Object>, GetWatchlistResult> {
    private final GetWatchlistActivity getWatchlistActivity;

    /**
     * Constructs a GetWatchlistLambda instance.
     * Initializes the GetWatchlistActivity using Dagger dependency injection.
     */
    public GetWatchlistLambda() {
        ServiceComponent component = DaggerServiceComponent.create();
        this.getWatchlistActivity = component.provideGetWatchlistActivity();
    }

    /**
     * Handles the Lambda function request to get a watchlist.
     *
     * @param input   the input map containing the watchlist ID
     * @param context the Lambda execution context
     * @return the result containing the retrieved watchlist
     */
    @Override
    public GetWatchlistResult handleRequest(Map<String, Object> input, Context context) {
        String watchlistId = (String) input.get("id");
        GetWatchlistRequest request = GetWatchlistRequest.builder().withId(watchlistId).build();
        return getWatchlistActivity.handleRequest(request);
    }
}

