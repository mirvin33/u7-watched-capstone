package com.nashss.se.watched.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.watched.activity.request.CreateWatchlistRequest;
import com.nashss.se.watched.activity.results.CreateWatchlistResult;
import com.nashss.se.watched.dependency.DaggerServiceComponent;
import com.nashss.se.watched.dependency.ServiceComponent;

/**
 * Lambda function to handle creating a new watchlist.
 */
public class CreateWatchlistLambda implements RequestHandler<CreateWatchlistRequest, CreateWatchlistResult> {
    private final ServiceComponent serviceComponent;

    public CreateWatchlistLambda() {
        this.serviceComponent = DaggerServiceComponent.create();
    }

    @Override
    public CreateWatchlistResult handleRequest(CreateWatchlistRequest request, Context context) {
        return serviceComponent.provideCreateWatchlistActivity().handleRequest(request);
    }
}