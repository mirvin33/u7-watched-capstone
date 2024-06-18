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
public class GetWatchlistsForUserLambda
        extends LambdaActivityRunner<GetWatchlistsForUserRequest, GetWatchlistsForUserResult>
        implements RequestHandler<LambdaRequest<GetWatchlistsForUserRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetWatchlistsForUserRequest> input, Context context) {
        return super.runActivity(() -> input.fromBody(GetWatchlistsForUserRequest.class),
                (request, serviceComponent) -> serviceComponent.provideGetWatchlistsForUserActivity()
                        .handleRequest(request)
        );
    }
}



