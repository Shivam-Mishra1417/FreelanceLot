package models;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * This class is used to test the Project Model Methods
 * @author Yagnik Pansuriya
 */
public class StatsTest {

   private static Map<String,Long> individual_sorted=new HashMap<>();
    private static Map<String,Long> global_sorted=new HashMap<>();
    private static Stats statstest;

    /**
     * This method is used to setup the testing data
     */
    @BeforeClass
    public static void setup(){
        individual_sorted.put("My",1L);
        individual_sorted.put("name",1L);
        individual_sorted.put("is",2L);
        individual_sorted.put("gagan",2L);
        individual_sorted.put("I",1L);
        individual_sorted.put("am",1L);

        global_sorted.put("My",1L);
        global_sorted.put("name",1L);
        global_sorted.put("is",2L);
        global_sorted.put("gagan",2L);
        global_sorted.put("I",1L);
        global_sorted.put("am",1L);
        statstest=new Stats("1234","Hello Test","My name is gagan. I am gagagn.",individual_sorted,global_sorted);
    }

    /**
     * This method is used to test the getter for getProjectID Method
     */
    @Test
    public void getproject_id_test() {
    String expected_projectID = statstest.getProject_id();
        assertEquals("1234",expected_projectID);
    }

    /**
     * This method is used to test the getter for getProjectTitle Method
     */
    @Test
    public void getproject_title_test() {
        assertEquals("Hello Test",statstest.getProject_title());
    }

    /**
     * This method is used to test the getter for getProjectPreviewDescription Method
     */
    @Test
    public void getproject_description_test() {
        assertEquals("My name is gagan. I am gagagn.",statstest.getProject_description());
    }

    /**
     * This method is used to test the getter for getIndividualSorted Method
     */
    @Test
    public void getIndividualSorted_test(){
        String preview_description = "My name is gagan. I am gagagn.";
        Map<String,Long> individual_sortedtest=new HashMap<>();
        individual_sortedtest.put("My",1L);
        individual_sortedtest.put("name",1L);
        individual_sortedtest.put("is",2L);
        individual_sortedtest.put("gagan",2L);
        individual_sortedtest.put("I",1L);
        individual_sortedtest.put("am",1L);

        //individual_sorted.put("Test1",value);
        assertThat(individual_sortedtest,is(statstest.getIndividual_sorted()));
    }

    /**
     * This method is used to test the getter for getGlobalSorted Method
     */
    @Test
    public void getGlobalSorted_test(){
        String preview_description = "My name is gagan. I am gagagn.";
        Map<String,Long> global_sorted=new HashMap<>();
        global_sorted.put("My",1L);
        global_sorted.put("name",1L);
        global_sorted.put("is",2L);
        global_sorted.put("gagan",2L);
        global_sorted.put("I",1L);
        global_sorted.put("am",1L);

        //individual_sorted.put("Test1",value);
        assertThat(global_sorted,is(statstest.getGlobal_sorted()));
    }

}

