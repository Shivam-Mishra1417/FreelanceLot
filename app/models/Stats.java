package models;

import java.util.List;
import java.util.Map;

/**
 * This is the model class for the stats
 * @author Yagnik Pansuriya
 *
 */

public class Stats {

    String project_id;
    String project_title;
    String project_description;
    Map<String,Long> individual_sorted;
    Map<String,Long> global_sorted;


    /**
     * Parameterised Constructor with the required parameters of the class.
     * @param project_id                project id
     * @param project_title             project title
     * @param project_description       project description
     * @param individual_sorted         individual sorting
     * @param global_sorted             global sorting
     */
    public Stats(String project_id, String project_title, String project_description, Map<String, Long> individual_sorted,Map<String, Long> global_sorted) {
        this.project_id = project_id;
        this.project_title = project_title;
        this.project_description = project_description;
        this.individual_sorted = individual_sorted;
        this.global_sorted = global_sorted;
    }
    /**
     * This method returns the project id
     * @return Integer
     */
    public String getProject_id() {
        return project_id;
    }
    /**
     * This method returns the project title
     * @return String
     */
    public String getProject_title() {
        return project_title;
    }
    /**
     * This method returns the project description
     * @return String
     */
    public String getProject_description() {
        return project_description;
    }
    /**
     * This method returns the individual sorting of individual the project's preview description
     * @return Hashmap
     */
    public Map<String, Long> getIndividual_sorted() {
        return individual_sorted;
    }
    /**
     * This method returns the global sorting of all the project's preview description
     * @return Hashmap
     */
    public Map<String, Long> getGlobal_sorted() {
        return global_sorted;
    }
}
