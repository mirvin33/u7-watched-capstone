package com.nashss.se.watched.lambda;

import com.nashss.se.watched.activity.request.UpdateWatchlistRequest;
import com.nashss.se.watched.activity.results.UpdateWatchlistResult;
import com.nashss.se.watched.dependency.DaggerServiceComponent;
import com.nashss.se.watched.dependency.ServiceComponent;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * Lambda function to handle updating a watchlist.
 */
public class UpdateWatchlistLambda implements RequestHandler<UpdateWatchlistRequest, UpdateWatchlistResult> {
    private final ServiceComponent serviceComponent;

    /**
     * Constructs an UpdateWatchlistLambda with a ServiceComponent.
     */
    public UpdateWatchlistLambda() {
        this.serviceComponent = DaggerServiceComponent.create();
    }

    /**
     * Handles the request to update a watchlist.
     *
     * @param request the request containing the ID of the watchlist to update and the new title
     * @param context the Lambda execution environment context
     * @return the result containing the updated watchlist
     */
    @Override
    public UpdateWatchlistResult handleRequest(UpdateWatchlistRequest request, Context context) {
        return serviceComponent.provideUpdateWatchlistActivity().handleRequest(request);
    }
}

