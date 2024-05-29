package com.nashss.se.watched.lambda;

import com.nashss.se.watched.activity.request.GetWatchlistRequest;
import com.nashss.se.watched.activity.results.GetWatchlistResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;

import java.util.logging.Logger;

/**
 * AWS Lambda function handler for getting a watchlist.
 */
public class GetWatchlistLambda  extends LambdaActivityRunner<GetWatchlistRequest, GetWatchlistResult>
        implements RequestHandler<LambdaRequest<GetWatchlistRequest>, LambdaResponse> {

    private final Logger log = (Logger) LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetWatchlistRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(() -> input.fromPath(path ->
                        GetWatchlistRequest.builder()
                                .withId(path.get("id"))
                                .build()), (request, serviceComponent) ->
                        serviceComponent.provideGetWatchlistActivity().handleRequest(request)
        );
    }
}

