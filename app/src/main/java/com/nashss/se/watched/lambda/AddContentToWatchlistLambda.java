package com.nashss.se.watched.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.watched.activity.request.AddContentToWatchlistRequest;
import com.nashss.se.watched.activity.results.AddContentToWatchlistResult;
import com.nashss.se.watched.dependency.DaggerServiceComponent;
import com.nashss.se.watched.dependency.ServiceComponent;

/**
 * Lambda function to handle adding content to a watchlist.
 */
public class AddContentToWatchlistLambda implements RequestHandler<AddContentToWatchlistRequest, AddContentToWatchlistResult> {
    private final ServiceComponent serviceComponent;

    public AddContentToWatchlistLambda() {
        this.serviceComponent = DaggerServiceComponent.create();
    }

    @Override
    public AddContentToWatchlistResult handleRequest(AddContentToWatchlistRequest request, Context context) {
        return serviceComponent.provideAddContentToWatchlistActivity().handleRequest(request);
    }
}
