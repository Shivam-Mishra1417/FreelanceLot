package businesslogic;

import models.ProjectList;
import models.projects;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class is used to test the skills from the data
 * @author Rahul patel
 */
public class SkillsResultTest {


   private static projects proj;
    private static ArrayList<String> skills = new ArrayList<>();
    List<projects> ar = new ArrayList<projects>();
    ArrayList<String> skillslist = new ArrayList<>();
    SkillsResult mock_list = mock(SkillsResult.class);

    /**
     * This is method is used for setting up the test data
     *
     */
    @Before
    public void setup(){
        skills.add("Java");
        proj = new projects(1, 5, "JSON Test", "Hello JSON World", skills, 97.08, 1.31, "5th Grade","13-12-2020","fixed");
        skillslist.add("Rahul");
        skillslist.add("Shivam");
        ar.add(new projects(1417, 1417, "Test_Freelancelot", "Testing Freelancelot", skillslist, 14.17, 14.17, "Early", "20 Mar 2022", "Fixed"));
//            KeyResults mock_list = mock(KeyResults.class);
        when(mock_list.getSkills("Prolog")).thenReturn(ar);
    }

    /**
     * This method is used to test the skills data from JSON
     */
    @Test
    public void TestJSONSkillsData(){
        String DummyData = "{\"result\":{\"projects\":[{\"title\":\"JSON Test\",\"owner_id\":5,\"id\":1,\"type\":\"fixed\",\"submitdate\":1647797879,\"preview_description\":\"Hello JSON World\",\"jobs\":[{\"id\":7,\"name\":\"Java\"}]}]}}";
        SkillsResult s1=new SkillsResult();
        List<projects> p1=s1.getJSONData(DummyData);
        projects expected=p1.get(0);
        assertEquals(proj.getId(),expected.getId());
    }

    /**
     * This method is used to test the getter method of SkillsResult Class
     */
    @Test
    public void testgetSkillData() {
//            KeyResults k1 = new KeyResults();
        List<projects> op = mock_list.getSkills("Prolog");
//            System.out.println();
        assertEquals(op, ar);
    }




}