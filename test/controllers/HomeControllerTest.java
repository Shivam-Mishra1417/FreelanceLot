package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import java.io.IOException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testIndex() throws ExecutionException, InterruptedException {
        CompletionStage<Result> result = new HomeController().index("Java");
        Result res = result.toCompletableFuture().get();
        assertEquals(OK, res.status());
        //assertEquals("text/html", result.contentType().get());
       // assertEquals("utf-8", result.charset().get());
       // assertTrue(contentAsString(result).contains("Welcome"));
    }
//    @Test
//    public void testIndex() {
//        Http.RequestBuilder request = new Http.RequestBuilder()
//                .method(GET)
//                .uri("/");
//
//        Result result = route(app, request);
//        assertEquals(OK, result.status());
//    }

    @Test
    public void testgetSearchResults(){
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/laura");

        Result result = route(app, request);
        assertEquals(OK, result.status());

    }

    @Test
    public void testgetSkillsResult(){
        System.out.println("Starting here");
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/skill/laura");
              Result result=null;
             result = route(app, request);
            System.out.println("xxhh - "+result.status());
            assertEquals(OK, result.status());



    }
    @Test
    public void testUser() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/user/787878");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testglobalstat() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/globalstat/");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testindividualStat() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/individualStat/12,java,laura");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

}
