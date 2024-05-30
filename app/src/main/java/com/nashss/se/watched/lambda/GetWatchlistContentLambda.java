package com.nashss.se.watched.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.watched.activity.request.GetWatchlistContentRequest;
import com.nashss.se.watched.activity.results.GetWatchlistContentResult;
import com.nashss.se.watched.dependency.DaggerServiceComponent;
import com.nashss.se.watched.dependency.ServiceComponent;

/**
 * Lambda function to handle retrieving watchlist content.
 */
public class GetWatchlistContentLambda implements RequestHandler<GetWatchlistContentRequest, GetWatchlistContentResult> {
    private final ServiceComponent serviceComponent;

    public GetWatchlistContentLambda() {
        this.serviceComponent = DaggerServiceComponent.create();
    }

    @Override
    public GetWatchlistContentResult handleRequest(GetWatchlistContentRequest request, Context context) {
        return serviceComponent.provideGetWatchlistContentActivity().handleRequest(request);
    }
}

