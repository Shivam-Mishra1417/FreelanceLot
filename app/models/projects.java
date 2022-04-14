package models;

import java.util.ArrayList;
import java.util.Map;

/**
 *This is the model class for the projects
 * @author group part
 */
public class projects {
    private int id;
    private int owner_id;
    private String title;
    private String desc;
    private ArrayList<String> skills;
    private double fleschReadIndex;
    private double fleschKincaid;
    private String EduLevel;
    Map<String,Long> individual_sorted;
    String date;
    String type;

    /**
     * Parameterised Constructor with the required parameters of the class.
     * @param id                project id
     * @param owner_id          owner id
     * @param title             project title
     * @param desc              project description
     * @param skills            skills
     * @param fleschReadIndex   flesch readabilty Index
     * @param fleschKincaid     flesch readbilty kincaid index
     * @param eduLevel          education level
     * @param date              date
     * @param type              type
     */
    public projects(int id, int owner_id, String title, String desc, ArrayList<String> skills, double fleschReadIndex, double fleschKincaid, String eduLevel, String date, String type) {
        this.id = id;
        this.owner_id = owner_id;
        this.title = title;
        this.desc = desc;
        this.skills = skills;
        this.fleschReadIndex = fleschReadIndex;
        this.fleschKincaid = fleschKincaid;
        EduLevel = eduLevel;
        this.date = date;
        this.type=type;
    }

    /**
     * This method returns the project id
     * @return Integer
     */
    public int getId() {
        return id;
    }

    /**
     * This method returns the owner id
     * @return Integer
     */
    public int getOwner_id() {
        return owner_id;
    }

    /**
     * This method returns the project title
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method returns the project description
     * @return String
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This method returns the skills
     * @return List
     */
    public ArrayList<String> getSkills() {
        return skills;
    }

    /**
     * This method returns the flesch readabilty Index
     * @return Double
     */
    public double getFleschReadIndex() {
        return fleschReadIndex;
    }

    /**
     * This method returns the flesch kincaid readabilty index
     * @return Double
     */
    public double getFleschKincaid() {
        return fleschKincaid;
    }

    /**
     * This method returns the Education level
     * @return String
     */
    public String getEduLevel() {
        return EduLevel;
    }

    /**
     * This method returns the type
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * This method returns the date
     * @return String
     */
    public String getDate() {
        return date;
    }
}