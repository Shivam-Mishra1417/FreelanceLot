package models;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the Employer Model methods
 * @author Shivam Mishra
 */
public class EmployerTest {
    private static Employer empl;
    private static ArrayList<String> skills;
    private static Emp_Status es;
    private static projects pro1;
    private static List<projects> hello;

    /**
     * This method is used to setup the testing data
     */
    @BeforeClass
    public static void setup(){
        pro1=new projects(1234,909090,"Proj Title","This is Description",skills,8989,76666,"9th","25 JAN 2022","fixed");
        es=new Emp_Status("verify","yes","no","yes","no","yes","yes","yes","no");
        empl=new Employer("123","Rahul","yes","Java Developer","7","13-12-1998","yes","rahul_10","Developer","India","Mumbai","yes",es,"INR","Hindi","15","Rahul","Google",hello);
    }

    /**
     * This method is used to test the getter method for Project ID
     */
    @Test
    public void getIDTest() {
        assertEquals("123",empl.getId());
    }

    /**
     * This method is used to test the getter method for Project Name
     */
    @Test
    public void getUserNameTest() {
        assertEquals("Rahul",empl.getUsername());
    }

    /**
     * This method is used to test the project close status
     */
    @Test
    public void isClosedTest() {
        assertEquals("yes",empl.getClosed());
    }

    /**
     * This method is used to test the getter method for Project Profile Description
     */
    @Test
    public void getProfileDescTest(){
        assertEquals("Java Developer",empl.getProfile_description());
    }

    /**
     * This method is used to test the getter method for Project Submission Date
     */
    @Test
    public void getDateTest(){
        assertEquals("13-12-1998",empl.getRegistration_date());
    }

    /**
     * This method is used to test the getter method for Project Limit
     */
    @Test
    public void getLimitedTest(){
        assertEquals("yes",empl.getLimited_account());
    }

    /**
     * This method is used to test the getter method for Project Name
     */
    @Test
    public void getDisplayTest(){
        assertEquals("rahul_10",empl.getDisplay_name());
    }

    /**
     * This method is used to test the getter method for Employer Project Tag
     */
    @Test
    public void getTagTest(){
        assertEquals("Developer",empl.getTagline());
    }

    /**
     * This method is used to test the getter method for Project Country
     */
    @Test
    public void getCountryTest(){
        assertEquals("India",empl.getCountry());
    }

    /**
     * This method is used to test the getter method for Project City
     */
    @Test
    public void getCityTest(){
        assertEquals("Mumbai",empl.getCity());
    }

    /**
     * This method is used to test the getter method for Project Role
     */
    @Test
    public void getRoleTest(){
        assertEquals("yes",empl.getRole());
    }

    /**
     * This method is used to test the getter method for Project Name
     */
    @Test
    public void getCurrencyTest(){
        assertEquals("INR",empl.getCurrency());
    }

    /**
     * This method is used to test the getter method for Project Language
     */
    @Test
    public void getLanguage_Test(){
        assertEquals("Hindi",empl.getLanguage());
    }

    /**
     * This method is used to test the getter method for Project Portfolio Count
     */
    @Test
    public void getPortfolioCountTest(){
        assertEquals("15",empl.getPortfolio_count());
    }

    /**
     * This method is used to test the getter method for Project Display Name
     */
    @Test
    public void getDisplayNameTest(){
        assertEquals("rahul_10",empl.getDisplay_name());
    }

    /**
     * This method is used to test the getter method for Project Company
     */
    @Test
    public void getCompanyTest(){
        assertEquals("Google",empl.getCompany());
    }

    /**
     * This method is used to test the getter method for Project Employer Status
     */
    @Test
    public void getEsTest(){
        assertEquals(es,empl.getEs());
    }

    /**
     * This method is used to test the getter method for Project Public Name
     */
    @Test
    public void getPublicNameTest(){
        assertEquals("Rahul",empl.getPublic_name());
    }

    /**
     * This method is used to test the getter method for Project Hourly rate
     */
    @Test
    public void getHourlyRateTest(){
        assertEquals("7",empl.getHourly_rate());
    }

    /**
     * This method is used to test the getter method for all Projects
     */
    @Test
    public void getAllProjectsTest(){
        assertEquals(hello,empl.getPro());
    }
}
