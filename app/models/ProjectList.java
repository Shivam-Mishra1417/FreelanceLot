package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *This is the class where the data is linked with the serach result
 * @author group part
 */

public class ProjectList {
    String searchedKeyword;
    List<projects> projectsList = new ArrayList<>();
    double avgFleshIndex;
    double avgFKGL;

    /**
     *Constructor of the class.
     */
    public ProjectList(){

     this.searchedKeyword="";
    this.projectsList = new ArrayList<>();
    }


    /**
     * This method returns the search query.
     * @return String
     */
    public String getSearchedKeyword() {
        return searchedKeyword;
    }

    /**
     * This method returns the List of projects.
     * @return List
     */
    public List<projects> getProjectsList() {
        return projectsList;
    }

    //public Map<String,List<projects>> getProjectMap(){ return projectMap;}
    /**
     * This method returns the List of the first ten projects.
     * @return List
     */
    public List<projects> getFirstTenProjectsList()
    {
        return this.projectsList.stream().limit(10).collect(Collectors.toList());
    }

    /**
     * This method sets the value of the search query.
     * @param searchedKeyword       search keyword
     */
    public void setSearchedKeyword(String searchedKeyword) {
        this.searchedKeyword = searchedKeyword;
    }
    /**
     * This method sets the value of the projects in List.
     * @param projectsList       List of projects
     */

    public void setProjectsList(List<projects> projectsList) {
        this.projectsList = projectsList;
    }

    /**
     * This method returns the average of flesch readabilty Index.
     * @return double
     */
    public double getAvgFleshIndex() {
        return avgFleshIndex;
    }

    /**
     * This method sets the average of flesch readabilty Index.
     * @param avgFleshIndex Average flesch readabilty Index
     */
    public void setAvgFleshIndex(double avgFleshIndex) {
        this.avgFleshIndex = avgFleshIndex;
    }

    /**
     * This method sets the average of flesch readabilty Index.
     * @param avgFKGL Average flesch kincaid readabilty Index
     */
    public void setAvgFKGL(double avgFKGL) {
        this.avgFKGL = avgFKGL;
    }

    /**
     * This method returns the average of flesch kincaid readabilty Index.
     * @return double
     */
    public double getAvgFKGL() {
        return avgFKGL;
    }
}
