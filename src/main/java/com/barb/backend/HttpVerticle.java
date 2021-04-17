package com.barb.backend;

import io.vertx.core.Promise;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.http.HttpServerResponse;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;

public class HttpVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        router.route("/").handler(this::indexHandler);

        final Integer applicationPort = config().getInteger(Constants.APPLICATION_PORT, 8080);

        vertx.createHttpServer()
                .requestHandler(router)
                .rxListen(applicationPort)
                .subscribe(
                        success -> startPromise.complete(),
                        error -> startPromise.fail(error.getCause())
                );
    }

    private void indexHandler(RoutingContext routingContext) {

        final HttpServerResponse response = routingContext.response();
        response
                .putHeader("content-type", "text/plain")
                .end("App is running just fine...");
    }
}
