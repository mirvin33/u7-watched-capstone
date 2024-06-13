package com.nashss.se.watched.lambda;

import com.nashss.se.watched.activity.request.CreateWatchlistRequest;
import com.nashss.se.watched.activity.results.CreateWatchlistResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Lambda function to handle creating a new watchlist.
 */
public class CreateWatchlistLambda
        extends LambdaActivityRunner<CreateWatchlistRequest, CreateWatchlistResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateWatchlistRequest>, LambdaResponse> {
    private final Logger log = LogManager.getLogger();

    /**
     * Handles the request to create a new watchlist.
     *
     * @param request the request containing the details for creating the watchlist
     * @param context the Lambda execution environment context
     * @return the result containing the created watchlist
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateWatchlistRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateWatchlistRequest unauthorizedRequest = input.fromPath(path ->
                            CreateWatchlistRequest.builder()
                                    .withUserId(path.get("userId"))
                                    .withTitle(path.get("title"))
                                    .build());
                    CreateWatchlistRequest createWatchlistRequest = input.fromBody(CreateWatchlistRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateWatchlistRequest.builder()
                                    .withUserId(claims.get("email"))
                                    .withTitle(createWatchlistRequest.getTitle())
                                    .build());
               },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateWatchlistActivity().handleRequest(request));
    }
}

