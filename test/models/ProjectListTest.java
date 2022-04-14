package models;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test the Project List Model Methods
 * @author Group Part
 */
public class ProjectListTest {

    private static ProjectList proj;
    private static ArrayList<String> skills;
    private static List<projects> hello=new ArrayList<>();

    /**
     * This method is used to setup the testing data
     */
    @BeforeClass
    public static void setup(){
        proj=new ProjectList();
    }

    /**
     * This method is used to test the getter method for Search Method
     */
    @Test
    public void getSearchTest(){
        assertEquals("",proj.getSearchedKeyword());
    }
    /*
    @Test
    public void getAvgFlesch(){
        assertEquals(0,proj.getAvgFleshIndex(),0);
    }
    @Test
    public void getAvgFKGL(){
        assertEquals(0,proj.getAvgFKGL(),0);
    }*/
    /**
     * This method is used to test the getter method for First Ten Projects Method
     */
    @Test
    public void getFirstTen(){
        assertEquals(hello,proj.getFirstTenProjectsList());
    }

    /**
     * This method is used to test the getter method for getAllMethod
     */
    @Test
    public void getAll(){
        assertEquals(hello,proj.getProjectsList());
    }

    /**
     * This method is used to test the setter method for Search Method
     */
    @Test
    public void setSearchTest(){
        ProjectList proj1=new ProjectList();
        proj1.setSearchedKeyword("Java Developer");
        assertEquals("Java Developer",proj1.getSearchedKeyword());
    }

    /**
     * This method is used to test the setter method for setProject Method
     */
    @Test
    public void setProjectTest(){
        ProjectList proj2=new ProjectList();
        List<projects> hello1=new ArrayList<>();
        proj2.setProjectsList(hello1);
        assertEquals(hello,proj.getFirstTenProjectsList());
    }

    /**
     * This method is used to test the setter method for setting Average Flesch Index
     */
    @Test
    public void setAvgFlesh(){
        ProjectList proj2=new ProjectList();
        proj2.setAvgFleshIndex(090d);
        assertEquals(090d,proj2.getAvgFleshIndex(),0);
    }

    /**
     * This method is used to test the setter method for setting Average Flesch-Kincaid Index
     */
    @Test
    public void setAvgFKGL(){
        ProjectList proj2=new ProjectList();
        proj2.setAvgFKGL(0999d);
        assertEquals(0999d,proj2.getAvgFKGL(),0);
    }
}
