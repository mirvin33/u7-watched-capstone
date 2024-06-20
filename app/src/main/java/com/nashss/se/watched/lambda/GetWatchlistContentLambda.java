package com.nashss.se.watched.lambda;

import com.nashss.se.watched.activity.request.GetWatchlistContentRequest;
import com.nashss.se.watched.activity.results.GetWatchlistContentResult;
import com.nashss.se.watched.dependency.DaggerServiceComponent;
import com.nashss.se.watched.dependency.ServiceComponent;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Lambda function to handle retrieving watchlist content.
 */
public class GetWatchlistContentLambda implements RequestHandler<GetWatchlistContentRequest,
        GetWatchlistContentResult> {

    private final ServiceComponent serviceComponent;

    /**
     * Constructs a GetWatchlistContentLambda with a ServiceComponent.
     */
    public GetWatchlistContentLambda() {
        this.serviceComponent = DaggerServiceComponent.create();
    }

    /**
     * Handles the request to retrieve watchlist content.
     *
     * @param request the request containing the ID of the watchlist to retrieve content for
     * @param context the Lambda execution environment context
     * @return the result containing the retrieved watchlist content
     */
    @Override
    public GetWatchlistContentResult handleRequest(GetWatchlistContentRequest request, Context context) {
        return serviceComponent.provideGetWatchlistContentActivity().handleRequest(request);
    }
}

