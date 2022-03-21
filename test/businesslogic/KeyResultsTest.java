package businesslogic;

import models.ProjectList;
import models.projects;

import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * This class is used to test the Keyresults class using junit and mockito
 * @author group part
 */
@RunWith(MockitoJUnitRunner.Silent.class)

public class KeyResultsTest {
    HashMap<String,Integer> skills=new HashMap<String,Integer>();
    List<ProjectList> globalResult = new ArrayList<>();
    KeyResults mock_list = mock(KeyResults.class);
    KeyResults k2 = new KeyResults();
    /**
     * This method is used to mock the data for test cases
     *
     */
    @Before
    public void testGetProjectData_mock() {
        List<projects> ar = new ArrayList<projects>();
        ArrayList<String> skillslist = new ArrayList<>();
        ProjectList projectList = new ProjectList();

        skillslist.add("Rahul");
        skillslist.add("Shivam");
        ar.add(new projects(1417, 1417, "Test_Freelancelot", "Testing Freelancelot", skillslist, 14.17, 14.17, "Early", "20 Mar 2022", "Fixed"));
        projectList.setSearchedKeyword("Prolog");
        projectList.setProjectsList(ar);
        projectList.setAvgFleshIndex(14.17);
        projectList.setAvgFKGL(14.17);
        globalResult.add(projectList);
//            KeyResults mock_list = mock(KeyResults.class);
//
        when(mock_list.getProjectData("Prolog")).thenReturn(globalResult);
    }

    /**
     * This method is used to test the project data
     *
     */
    @Test
    public void testgetProjectData() {
//            KeyResults k1 = new KeyResults();
        List<ProjectList> op = mock_list.getProjectData("Prolog");
//            System.out.println();
        assertEquals(op, globalResult);
    }

    @Test
    public void testMain()
    {
        k2.main(null);
        assertTrue(true);
    }

//        @Test
//        public void addToSkillMapTest()
//        {
//            skills.put("PHP",3);
//            assertThat(skills,is(k2.addToSkillMap("PHP",3)));
//        }

    /**
     * This method is used to test the json data
     */
    @Test
    public void getJSONKeyTest(){
        KeyResults k1=new KeyResults();
        String Data = "{\"result\":{\"projects\":[{\"title\":\"JSON Test\",\"owner_id\":5,\"id\":1,\"type\":\"fixed\",\"submitdate\":1647797879,\"preview_description\":\"Hello JSON World\",\"jobs\":[{\"id\":7,\"name\":\"Java\"}]}]}}";
        List<ProjectList> uni=k1.getKeyData(Data,"Java");
        ProjectList proj=uni.get(0);
        assertEquals("Java",proj.getSearchedKeyword());
//            assertEquals("");
    }
    /**
     * This method is used to test the skills map data
     */
    @Test
    public void maptest(){
        KeyResults k1=new KeyResults();
        HashMap<String, Integer> map = new HashMap<>();
        KeyResults.addToSkillMap("Java",7);
        int compare=KeyResults.getToSkillMap("Java");
        assertEquals(7,compare);
    }
}