package com.nashss.se.watched.lambda;

import com.nashss.se.watched.activity.request.CreateWatchlistRequest;
import com.nashss.se.watched.activity.request.GetWatchlistsForUserRequest;
import com.nashss.se.watched.activity.results.GetWatchlistsForUserResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Lambda function to handle retrieving watchlists for a user.
 */
public class GetWatchlistsForUserLambda
        extends LambdaActivityRunner<GetWatchlistsForUserRequest, GetWatchlistsForUserResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetWatchlistsForUserRequest>, LambdaResponse> {

    private final Logger log =  LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetWatchlistsForUserRequest> input, Context context) {
        log.info("handleRequest");
        // return super.runActivity(() -> input.fromPath(path ->
        //         GetWatchlistsForUserRequest.builder()
        //                 .withUserId(path.get("userId"))
        //                 .build()), (request, serviceComponent) ->
        //         serviceComponent.provideGetWatchlistsForUserActivity().handleRequest(request)
        // );
        return super.runActivity(
                () -> {
                    return input.fromUserClaims(claims ->
                            GetWatchlistsForUserRequest.builder()
                                    .withUserId(claims.get("email"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetWatchlistsForUserActivity().handleRequest(request)
            );
    }
}



