package com.nashss.se.watched.lambda;

import com.nashss.se.watched.activity.request.DeleteWatchlistRequest;
import com.nashss.se.watched.activity.results.DeleteWatchlistResult;
import com.nashss.se.watched.dependency.DaggerServiceComponent;
import com.nashss.se.watched.dependency.ServiceComponent;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * Lambda function to handle deleting a watchlist.
 */
public class DeleteWatchlistLambda implements RequestHandler<DeleteWatchlistRequest, DeleteWatchlistResult> {
    private final ServiceComponent serviceComponent;

    /**
     * Constructs a DeleteWatchlistLambda with a ServiceComponent.
     */
    public DeleteWatchlistLambda() {
        this.serviceComponent = DaggerServiceComponent.create();
    }

    /**
     * Handles the request to delete a watchlist.
     *
     * @param request the request containing the ID of the watchlist to delete
     * @param context the Lambda execution environment context
     * @return the result indicating the status of the delete operation
     */
    @Override
    public DeleteWatchlistResult handleRequest(DeleteWatchlistRequest request, Context context) {
        return serviceComponent.provideDeleteWatchlistActivity().handleRequest(request);
    }
}

