package controllers;
import businesslogic.*;
import play.mvc.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import play.cache.AsyncCacheApi;


/**
 * HomeController is the Controller that handles the HTTP requests for searching for information on given search query, for fetching the freelancer project data,
 * for fetching user data.
 *
 *
 * @author Group part
 *
 */

public class HomeController extends Controller {
    private AsyncCacheApi cache;
    //KeyResults results = new KeyResults();
    EmployerData ed = new EmployerData();
    SkillsResult skills=new SkillsResult();
//    function fn1 = new function();
    WordStats wordStats=new WordStats();
    private String CacheKey = "Index";
    String key;

    /**
     * This method takes the search term/query, fetches the data from the api call and renders the data in HTML.
     * @param query     Search Keyword
     * @return void
     */



    public CompletionStage<Result> index(String query) {
        return CompletableFuture
                .supplyAsync(() -> new KeyResults().getProjectData(query))
                .thenApply(i -> ok(views.html.index.render(i)));
    }

    /**
     * This method takes the search term/query from the url, fetches the data from the api call and renders the data in HTML.
     * @param query     Search Keyword
     * @return void
     */

    public CompletionStage<Result> getSearchResults(String query) {
        return CompletableFuture
                .supplyAsync(() -> new KeyResults().getProjectData(query))
                .thenApply(i -> ok(views.html.index.render(i)));
    }

    /**
     * This method searches and displays the projects from the user query and renders the specific employer data in HTML.
     * @param query     Search Keyword
     * @return void
     */

    public CompletionStage<Result> getEmployer(String query) {
       // String word= id.toString();
        return CompletableFuture
                .supplyAsync(() -> ed.getEmployer(query))
                .thenApply(j -> ok(views.html.employer.render(j)));
    }

    /**
     * This method fetches all the projects related to the given skill. It uses the Skill ID to get the data from the API call. The result is displayed in HTML.
     * @param skill_key       Skills ID
     * @return void
     */

    public CompletionStage<Result> getSkillsResult(String skill_key) {
        return CompletableFuture
                .supplyAsync(() -> skills.getSkills(skill_key))
                .thenApply(z -> ok(views.html.skills.render(z)));
    }

    /**
     * This method displays the word statistics for a particular project based on the project's preview description.
     * @param project_id              Project ID
     * @param project_title           Project Title
     * @param project_descr           Project Description
     * @return void
     */

    public CompletionStage<Result> getIndividualStat(String project_id,String project_title, String project_descr) {
        return CompletableFuture
                .supplyAsync(() -> wordStats.getWordstat(project_id,project_title,project_descr))
                .thenApply(z -> ok(views.html.wordstat.render(z)));
    }

    /**
     * This method displays the word statistics for all the project which are displayed based on the projects preview descriptions.
     * @return void
     */

    public CompletionStage<Result> getGlobalStat() {
        return CompletableFuture
                .supplyAsync(() -> wordStats.getGlobalWordstat())
                .thenApply(z -> ok(views.html.globalWordStat.render(z)));
    }

}
