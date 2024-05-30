package com.nashss.se.watched.lambda;

import com.nashss.se.watched.activity.request.GetWatchlistsForUserRequest;
import com.nashss.se.watched.activity.results.GetWatchlistsForUserResult;
import com.nashss.se.watched.dependency.DaggerServiceComponent;
import com.nashss.se.watched.dependency.ServiceComponent;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * Lambda function to handle retrieving watchlists for a user.
 */
public class GetWatchlistsForUserLambda implements RequestHandler<GetWatchlistsForUserRequest,
        GetWatchlistsForUserResult> {
    private final ServiceComponent serviceComponent;

    /**
     * Constructs a GetWatchlistsForUserLambda with a ServiceComponent.
     */
    public GetWatchlistsForUserLambda() {
        this.serviceComponent = DaggerServiceComponent.create();
    }

    /**
     * Handles the request to retrieve watchlists for a user.
     *
     * @param request the request containing the user ID
     * @param context the Lambda execution environment context
     * @return the result containing the retrieved watchlists
     */
    @Override
    public GetWatchlistsForUserResult handleRequest(GetWatchlistsForUserRequest request, Context context) {
        return serviceComponent.provideGetWatchlistsForUserActivity().handleRequest(request);
    }
}

