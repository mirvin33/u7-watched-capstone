package com.nashss.se.watched.lambda;

import com.nashss.se.watched.activity.request.AddContentToWatchlistRequest;
import com.nashss.se.watched.activity.results.AddContentToWatchlistResult;
import com.nashss.se.watched.dependency.DaggerServiceComponent;
import com.nashss.se.watched.dependency.ServiceComponent;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * Lambda function to handle adding content to a watchlist.
 */
public class AddContentToWatchlistLambda
        extends LambdaActivityRunner<AddContentToWatchlistRequest, AddContentToWatchlistResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddContentToWatchlistRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddContentToWatchlistRequest> input, Context context)
    { return super.runActivity(
                () -> {
                   AddContentToWatchlistRequest unauthenticatedRequest = input
                           .fromBody(AddContentToWatchlistRequest.class);
                    return input.fromUserClaims(claims ->
                            AddContentToWatchlistRequest.builder()
                                    .withId(unauthenticatedRequest.getId())
                                    .withUserId(claims.get("email"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddContentToWatchlistActivity().handleRequest(request)
        );
    }
}

