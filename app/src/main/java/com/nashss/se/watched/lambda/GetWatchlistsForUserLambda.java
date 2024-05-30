package com.nashss.se.watched.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.watched.activity.request.GetWatchlistsForUserRequest;
import com.nashss.se.watched.activity.results.GetWatchlistsForUserResult;
import com.nashss.se.watched.dependency.DaggerServiceComponent;
import com.nashss.se.watched.dependency.ServiceComponent;

/**
 * Lambda function to handle retrieving watchlists for a user.
 */
public class GetWatchlistsForUserLambda implements RequestHandler<GetWatchlistsForUserRequest, GetWatchlistsForUserResult> {
    private final ServiceComponent serviceComponent;

    public GetWatchlistsForUserLambda() {
        this.serviceComponent = DaggerServiceComponent.create();
    }

    @Override
    public GetWatchlistsForUserResult handleRequest(GetWatchlistsForUserRequest request, Context context) {
        return serviceComponent.provideGetWatchlistsForUserActivity().handleRequest(request);
    }
}


