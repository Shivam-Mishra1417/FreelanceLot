package controllers;

import org.junit.Ignore;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import java.util.UUID;

import static org.junit.Assert.*;
import static play.mvc.Http.HttpVerbs.POST;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testIndex() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testIndividualStat() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/individualStat/java");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
    @Ignore
    public void socket() {
        Http.RequestBuilder request = new Http.RequestBuilder().method(GET).uri("/chat");
        Result res = route(app,request);
//        assertNotEquals(OK, res.status());
        assertNull(res.status());
    }


    @Ignore
    public void akkaStreamsSocket() {
        Http.RequestBuilder request = new Http.RequestBuilder().method(GET).uri("/chat/with/streams ");
        Result res = route(app,request);
        assertEquals(OK, res.status());
    }
    @Test
    public void getSkillsResult() {
        Http.RequestBuilder request = new Http.RequestBuilder().method(GET).uri("/skill/java");
        Result res = route(app,request);
        assertNotEquals(OK, res.status());
    }

    @Test
    public void getEmployer() {
        Http.RequestBuilder request = new Http.RequestBuilder().method(GET).uri("/user/232323");
        Result res = route(app,request);
        assertEquals(OK, res.status());
    }
    @Test
    public void getGlobalStat() {
        Http.RequestBuilder request = new Http.RequestBuilder().method(GET).uri("/GlobalStat/");
        Result res = route(app,request);
        assertEquals(OK, res.status());
    }
    @Ignore
    public void setCacheData() {
        Http.RequestBuilder request = new Http.RequestBuilder().method(POST).uri("/setCache");
        Result res = route(app,request);
        assertEquals(OK, res.status());
    }
    @Test
    public void getCacheId() {
        Http.RequestBuilder request = new Http.RequestBuilder().method(GET).uri("/getCacheId");
        Result res = route(app,request);
        assertEquals(OK, res.status());
    }

    @Test
    public void getCacheData() {
        Http.RequestBuilder request = new Http.RequestBuilder().method(GET).uri("/getCache/"+ UUID.randomUUID().toString());
        Result res = route(app,request);
        assertEquals(OK, res.status());
    }
}
