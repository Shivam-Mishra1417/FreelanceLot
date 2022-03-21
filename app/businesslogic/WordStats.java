package businesslogic;

import models.Stats;
import models.projects;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toMap;

/**
 * This class method is used to get the word stats from the project description of the project.
 * @author Yagnik Pansuriya
 */
public class WordStats {

    /**
     * This method returns the individual sorting of individual the project's preview description
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
     * This method returns the object of the individual word stat
     *
     * @param project_descr     Project description
     * @param project_id        Project id
     * @param project_title     project title
     * @return Hashmap
     */
    public Stats getWordstat(String project_id, String project_title, String project_descr ){
        WordStats wordStats=new WordStats();
        Map<String,Long> sorted1 = wordStats.wordStat(project_descr);
        Stats s1 = new Stats(project_id,project_title,project_descr,sorted1,null);
        return s1;
    }

    /**
     * This method returns the global word stats the project's preview description
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
    public Stats getGlobalWordstat(){
        WordStats wordStats=new WordStats();
        List<projects> project_data= KeyResults.global_list;
        List<String> project_descr = new ArrayList<>();
        for(projects project : project_data){
            project_descr.add(project.getDesc());
        }
        Map<String,Long> sorted1 = wordStats.globalWordStat(project_descr);
        Stats s1 = new Stats("","","",null,sorted1);
        return s1;
    }
}
