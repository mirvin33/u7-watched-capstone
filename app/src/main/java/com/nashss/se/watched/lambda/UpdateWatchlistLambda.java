package com.nashss.se.watched.lambda;

import com.nashss.se.watched.activity.request.UpdateWatchlistRequest;
import com.nashss.se.watched.activity.results.UpdateWatchlistResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Lambda function to handle updating a watchlist.
 */
public class UpdateWatchlistLambda
        extends LambdaActivityRunner<UpdateWatchlistRequest, UpdateWatchlistResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateWatchlistRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateWatchlistRequest> input, Context context) {
        return super.runActivity(() -> {
            UpdateWatchlistRequest pathRequest = input.fromPath(path ->
                    UpdateWatchlistRequest.builder()
                            .withId(path.get("id"))
                            .build());
            UpdateWatchlistRequest bodyRequest = input.fromBody(UpdateWatchlistRequest.class);
            return input.fromUserClaims(claims ->
                    UpdateWatchlistRequest.builder()
                            .withId(pathRequest.getId())
                            .withUserId(claims.get("email"))
                            .withTitle(bodyRequest.getTitle())
                            .build()); }, (request, serviceComponent) ->
                        serviceComponent.provideUpdateWatchlistActivity().handleRequest(request));
    }
}

