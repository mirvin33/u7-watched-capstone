package com.nashss.se.watched.lambda;

import com.nashss.se.watched.activity.request.CreateWatchlistRequest;
import com.nashss.se.watched.activity.results.CreateWatchlistResult;
import com.nashss.se.watched.dependency.DaggerServiceComponent;
import com.nashss.se.watched.dependency.ServiceComponent;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * Lambda function to handle creating a new watchlist.
 */
public class CreateWatchlistLambda implements RequestHandler<CreateWatchlistRequest, CreateWatchlistResult> {
    private final ServiceComponent serviceComponent;

    /**
     * Constructs a CreateWatchlistLambda with a ServiceComponent.
     */
    public CreateWatchlistLambda() {
        this.serviceComponent = DaggerServiceComponent.create();
    }

    /**
     * Handles the request to create a new watchlist.
     *
     * @param request the request containing the details for creating the watchlist
     * @param context the Lambda execution environment context
     * @return the result containing the created watchlist
     */
    @Override
    public CreateWatchlistResult handleRequest(CreateWatchlistRequest request, Context context) {
        return serviceComponent.provideCreateWatchlistActivity().handleRequest(request);
    }
}

