package models;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * This class is used to test the Project Model Methods
 * @author Group Part
 */
public class projectstest {
    private static  ArrayList<String> skills=new ArrayList<>();
    private static projects proj;

    /**
     * This method is used to setup the testing data
     */
    @BeforeClass
    public static void setup(){
        skills.add("Java");
        proj=new projects(1234,909090,"Proj Title","This is Description",skills,8989,76666,"9th","25 JAN 2022","fixed");
    }

    /**
     * This method is used to test the getter method for getID Method
     */
    @Test
    public void getIDTest() {
        int expected_projectID = proj.getId();
        assertEquals(1234,expected_projectID);
    }

    /**
     * This method is used to test the getter method for getOwnerID Method
     */
    @Test
    public void getOwnerId() {
        assertEquals(909090,proj.getOwner_id());
    }

    /**
     * This method is used to test the getter method for getTitle Method
     */
    @Test
    public void getTitleTest() {
        assertEquals("Proj Title",proj.getTitle());
    }

    /**
     * This method is used to test the getter method for getDesc Method
     */
    @Test
    public void getDescTest(){
        assertEquals("This is Description",proj.getDesc());
    }

    /**
     * This method is used to test the getter method for getSkills Method
     */
    @Test
    public void getSkillsTest(){
        ArrayList<String> skills=new ArrayList<>();
        skills.add("Java");
        assertThat(skills,is(proj.getSkills()));
    }

    /**
     * This method is used to test the Flesch Kincaid Index calculator Method
     */
    @Test
    public void fleshkincaid(){
        assertEquals(76666,proj.getFleschKincaid(),0);
    }

    /**
     * This method is used to test the Flesch Readability Index calculator Method
     */
    @Test
    public void fleshread(){
        assertEquals(8989,proj.getFleschReadIndex(),0);
    }

    /**
     * This method is used to test the getter for getEduLevel Method
     */
    @Test
    public void getEduLevel(){
        assertEquals("9th",proj.getEduLevel());
    }

    /**
     * This method is used to test the getter for getDate Method
     */
    @Test
    public void getDate(){
        assertEquals("25 JAN 2022",proj.getDate());
    }

    /**
     * This method is used to test the getter for getType Method
     */
    @Test
    public void getType(){
        assertEquals("fixed",proj.getType());
    }

}

