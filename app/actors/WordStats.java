package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import models.Stats;
import models.projects;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * This is the Actor class to handle the Word Statistic Functionality.
 * @author Yagnik Pansuriya
 */
public class WordStats extends AbstractActor {

    ActorSystem akkaSystem =  ActorSystem.create("System");
    ActorRef index = akkaSystem.actorOf(WebsocketActor.props());
    public static Props getProps() {
        return Props.create(WordStats.class);
    }


    /**
     * Defines which messages the WordStats actor can handle, along with the implementation of how the messages should be processed.
     * @return Receive
     */

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, words-> {
                    Stats  data = getWordstat(words);
                    sender().tell(data, self());
                })
                .match(Integer.class,words-> {
                    int t = words;
                    Stats  data = getGlobalWordstat();
                    sender().tell(data, self());
                })
                .build();
    }
    /**
     * This method returns word level statistics from the preview description.
     * @param preview_description       Project description
     * @return Hashmap
     */
    public Map<String,Long> wordStat(String preview_description){
        //String st = ".kjGUIGUG idhfoihyadihg";
//    Map<String,Long> temp = Arrays.stream(st.replaceAll("[^0-9A-Za-z ]+", "").split("\\s+"))
//            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String,Long> sorted = Arrays.stream(preview_description.replaceAll("[^0-9A-Za-z ]+", "").split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        sorted = sorted.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        //sorted.entrySet().forEach(System.out::println);
        return sorted;
    }

    /**
     * This method sets the computed word statistic in the Stats class.
     * @param project_descr       Project description
     * @return Stats
     */

    public Stats getWordstat(String project_descr ){
        //WordStats wordStats=new WordStats();
        Map<String,Long> sorted1 = wordStat(project_descr);
        Stats s1 = new Stats(null,null,project_descr,sorted1,null);
        return s1;
    }

    /**
     * This method returns the global word stats from the preview description
     * @param preview_description       Project description
     * @return Hashmap
     */
    public  Map<String,Long>  globalWordStat(List<String> preview_description){
        Map<String,Long> global_sorted = preview_description.stream().flatMap(line -> Arrays.stream(line.replaceAll("[^0-9A-Za-z ]+", "").split("\\s+")))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        global_sorted = global_sorted.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                LinkedHashMap::new));
        //global_sorted.entrySet().forEach(System.out::println);
        return global_sorted;
    }


    /**
     * This method returns the object of the global word stat
     * @return Hashmap
     */
    public Stats getGlobalWordstat() throws ExecutionException, InterruptedException {
        //  WordStats wordStats=new WordStats();
//        System.out.println("checkpoint 1");
//        CompletionStage<Object> response= FutureConverters.toJava(ask(index,1,Integer.MAX_VALUE));
        //List<projects> project_data=WebsocketActor.global_list;
//        System.out.println("checkpoint 2");
//         Object temp= response.toCompletableFuture().get();
//        System.out.println("checkpoint 2.1");
        List<projects> project_data =WebsocketActor.global_list;
//        System.out.println("checkpoint 3");
        List<String> project_descr = new ArrayList<>();
        for(projects project : project_data){
            project_descr.add(project.getDesc());
        }
        System.out.println("checkpoint 4");
        Map<String,Long> sorted1 = globalWordStat(project_descr);
        Stats s1 = new Stats("","","",null,sorted1);
        return s1;
    }


}

