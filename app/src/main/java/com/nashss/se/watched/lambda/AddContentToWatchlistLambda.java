package com.nashss.se.watched.lambda;

import com.nashss.se.watched.activity.request.AddContentToWatchlistRequest;
import com.nashss.se.watched.activity.results.AddContentToWatchlistResult;
import com.nashss.se.watched.dependency.DaggerServiceComponent;
import com.nashss.se.watched.dependency.ServiceComponent;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * Lambda function to handle adding content to a watchlist.
 */
public class AddContentToWatchlistLambda implements RequestHandler<AddContentToWatchlistRequest,
        AddContentToWatchlistResult> {
    private final ServiceComponent serviceComponent;

    /**
     * Constructs an AddContentToWatchlistLambda with a ServiceComponent.
     */
    public AddContentToWatchlistLambda() {
        this.serviceComponent = DaggerServiceComponent.create();
    }

    /**
     * Handles the request to add content to a watchlist.
     *
     * @param request the request containing the details for adding content to the watchlist
     * @param context the Lambda execution environment context
     * @return the result of adding content to the watchlist
     */
    @Override
    public AddContentToWatchlistResult handleRequest(AddContentToWatchlistRequest request, Context context) {
        return serviceComponent.provideAddContentToWatchlistActivity().handleRequest(request);
    }
}

