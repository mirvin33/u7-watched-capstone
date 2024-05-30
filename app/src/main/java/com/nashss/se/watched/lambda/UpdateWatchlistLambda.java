package com.nashss.se.watched.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.watched.activity.request.UpdateWatchlistRequest;
import com.nashss.se.watched.activity.results.UpdateWatchlistResult;
import com.nashss.se.watched.dependency.DaggerServiceComponent;
import com.nashss.se.watched.dependency.ServiceComponent;

public class UpdateWatchlistLambda implements RequestHandler<UpdateWatchlistRequest, UpdateWatchlistResult> {
    private final ServiceComponent serviceComponent;

    public UpdateWatchlistLambda() {
        this.serviceComponent = DaggerServiceComponent.create();
    }

    @Override
    public UpdateWatchlistResult handleRequest(UpdateWatchlistRequest request, Context context) {
        return serviceComponent.provideUpdateWatchlistActivity().handleRequest(request);
    }
}
