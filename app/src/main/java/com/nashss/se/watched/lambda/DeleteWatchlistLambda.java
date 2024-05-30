package com.nashss.se.watched.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.watched.activity.request.DeleteWatchlistRequest;
import com.nashss.se.watched.activity.results.DeleteWatchlistResult;
import com.nashss.se.watched.dependency.DaggerServiceComponent;
import com.nashss.se.watched.dependency.ServiceComponent;

/**
 * Lambda function to handle deleting a watchlist.
 */
public class DeleteWatchlistLambda implements RequestHandler<DeleteWatchlistRequest, DeleteWatchlistResult> {
    private final ServiceComponent serviceComponent;

    public DeleteWatchlistLambda() {
        this.serviceComponent = DaggerServiceComponent.create();
    }

    @Override
    public DeleteWatchlistResult handleRequest(DeleteWatchlistRequest request, Context context) {
        return serviceComponent.provideDeleteWatchlistActivity().handleRequest(request);
    }
}

