package com.nashss.se.watched.lambda;

import com.nashss.se.watched.activity.request.DeleteWatchlistRequest;
import com.nashss.se.watched.activity.results.DeleteWatchlistResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * Lambda function to handle deleting a watchlist.
 */
public class DeleteWatchlistLambda
        extends LambdaActivityRunner<DeleteWatchlistRequest, DeleteWatchlistResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteWatchlistRequest>, LambdaResponse> {
    /**
     * Handles the request to delete a watchlist.
     *
     * @param input the input
     * @param context the Lambda execution environment context
     * @return the result indicating the status of the delete operation
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteWatchlistRequest> input, Context context) {
        return super.runActivity(() -> {
            DeleteWatchlistRequest unauthenticatedRequest = input.fromPath(path ->
                    DeleteWatchlistRequest.builder()
                            .withId(path.get("id"))
                            .build());
            return input.fromUserClaims(claims ->
                    DeleteWatchlistRequest.builder()
                            .withId(unauthenticatedRequest.getId())
                            .withUserId(claims.get("email"))
                            .build()); }, (request, serviceComponent) ->
                        serviceComponent.provideDeleteWatchlistActivity().handleRequest(request)
        );
    }
}


