package controllers;

import actors.WebsocketActor;
import models.Stats;
import akka.actor.*;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import models.Employer;
import models.projects;
import play.libs.streams.ActorFlow;
import play.mvc.*;
import views.html.*;
import play.cache.*;
import javax.inject.Inject;
import java.time.Duration;

import actors.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Singleton;
import scala.compat.java8.FutureConverters;
import static akka.pattern.Patterns.ask;

/**
 *HomeController class is used to define the controller for the routes defined
 * @author group part
 */
@Slf4j
@Singleton
public class HomeController extends Controller {

    private final ActorRef skillsactor,useractor,indiactor;
    private String CacheKey = "Index";
    private AsyncCacheApi cache;
    //String key;
    //String data;
    private ActorSystem actorSystem;
    private Materializer materializer;
    // EmployerData ed = new EmployerData(); //sm
    //SkillsResult skills=new SkillsResult();
    //    function fn1 = new function();
    //WordStats wordStats=new WordStats();
    AtomicReference<ActorRef> value;

    /**
     * Parameterised Constructor with the required parameters of the class.
     * @param actorSystem               Actor System
     * @param materializer              Materializer
     * @param cache                     Async CacheApi
     */
    @Inject
    public HomeController(ActorSystem actorSystem, Materializer materializer, AsyncCacheApi cache) {

        this.actorSystem = actorSystem;
        this.materializer = materializer;
        this.cache = cache;
        skillsactor = actorSystem.actorOf(SkillsResult.getProps());
        useractor = actorSystem.actorOf(EmployerData.getProps());
        indiactor = actorSystem.actorOf(WordStats.getProps());
    }

    /**
     * This method is used to control index route.
     * @param request                    Http Request received from the browser
     */
    public CompletionStage<Result> index(Http.Request request) {
        String url = routes.HomeController.socket().webSocketURL(request);
        cache.removeAll();
        return cache.set("key",UUID.randomUUID().toString()).thenApply(i -> request
                .session()
                .get("connected")
                .map(user -> ok(index.render(url)))
                .orElseGet(() -> ok(index.render(url))));
    }

    /**
     * This method takes the request header, and returns a flow to handle the WebSocket messages.
     * @return  WebSocket
     */
    public WebSocket socket() {
        return WebSocket.Text.accept(out -> ActorFlow.actorRef(WebsocketActor::props, actorSystem,materializer));
    }

    /**
     * This method takes the request header, and creates a flow between input and output using Sink and Source.
     * @return  WebSocket
     */
    public WebSocket akkaStreamsSocket() {
        return WebSocket.Text.accept(
                request -> {
                    Sink in = Sink.foreach(out -> ActorFlow.actorRef(WebsocketActor::props, actorSystem,materializer));
                    Source out = Source.tick(Duration.ofSeconds(1), Duration.ofSeconds(15), in)
                            .map(res -> res.toString());
                    return Flow.fromSinkAndSource(in, out);
                });
    }

    /**
     * This method takes the preview description and returns the word level statistics.
     * @param desc           preview description
     * @return  Result
     */
    public CompletionStage<Result> getIndividualStat(String desc) {
        //  words w1=new words("","",desc);
        return FutureConverters.toJava(ask(indiactor, desc, Integer.MAX_VALUE))
                .thenApply(response -> ok(views.html.wordstat.render((Stats)response)));
    }

    /**
     * This method takes the skill, and return the Skill Result.
     * @param skill             Skill
     * @return  Result
     */
    public CompletionStage<Result> getSkillsResult(String skill) {
        return FutureConverters.toJava(ask(skillsactor, skill, Integer. MAX_VALUE))
                .thenApply(response -> ok(views.html.skills.render((List<projects>)response)));
    }

    /**
     * This method takes the Owner Id, and returns the Employer Details.
     * @param ownerid           Owner Id
     * @return  Result
     */
    public CompletionStage<Result> getEmployer(String ownerid) {

        return FutureConverters.toJava(ask(useractor, ownerid, Integer. MAX_VALUE))
                .thenApply(response -> ok(views.html.employer.render((List<Employer>)response)));
    }

    /**
     * This method gets the global word stats.
     * @return  Result
     */
    public CompletionStage<Result> getGlobalStat() {

        return FutureConverters.toJava(ask(indiactor,1, Integer. MAX_VALUE))
                .thenApply(response -> ok(views.html.globalstats.render((Stats)response)));
    }
    /**
     * This method takes the request header, and sets the data received in the Cache.
     * @param request           Http request
     * @return  Result
     */
    public CompletionStage<Result> setCacheData(Http.Request request){
        JsonNode json = request.body().asJson();
        return CompletableFuture
                .supplyAsync(() -> cache.set(json.get("id").toString(),json.get("value")))
                .thenApply(i -> ok("Done"));
    }

    /**
     * This method gets the Cache ID.
     * @return  WebSocket
     */
    public CompletionStage<Result> getCacheId(){
        return cache.get("key").thenApply(i -> ok(i.get().toString()));
    }

    /**
     * This method gets the Cache data.
     * @param data          Search Keyword
     * @return  Result
     */
    public CompletionStage<Result> getCacheData(String data){
        return cache.get(data).thenApply(i -> ok(i.get().toString()));
    }
    /*public CompletionStage<Result> getGlobalStat() {
        return CompletableFuture
                .supplyAsync(() -> wordStats.getGlobalWordstat())
                .thenApply(z -> ok(views.html.globalWordStat.render(z)));
    }*/



}