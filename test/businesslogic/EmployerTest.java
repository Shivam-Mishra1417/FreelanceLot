package businesslogic;

import models.Emp_Status;
import models.Employer;
import models.projects;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains the method for testing the employer data
 * @author Shivam Mishra
 */
public class EmployerTest {
    private static ArrayList<String> skills = new ArrayList<>();
    private static Map<String, Long> individual_sorted = new HashMap<>();
    private static projects proj;
    static EmployerData mock_list=mock(EmployerData.class);
    static List<Employer> e1=new ArrayList<>();
    static List<projects> ar = new ArrayList<projects>();
    /**
     * This is the setup method used before testing
     */
    @BeforeClass
    public static void setup() {
        individual_sorted.put("Hello", 1L);
        individual_sorted.put("JSON", 1L);
        individual_sorted.put("World", 1L);
        skills.add("Java");
        Emp_Status emp_status=new Emp_Status("false","true","no","yes","yes","yes","yes","yes","yes");
        proj = new projects(1, 5, "JSON Test", "Hello JSON World", skills, 97.08, 1.31, "5th Grade","27-12-2001","fixed");
        Employer a1 = new Employer(  "1","Shivam","no","yes","15","25-jan-2021","no","shivam","hello","canada","montreal","se",emp_status,"22","English","1","hello","company",ar);
        ar.add(new projects(1417, 1417, "Test_Freelancelot", "Testing Freelancelot", skills, 14.17, 14.17, "Early", "20 Mar 2022", "Fixed"));
        when(mock_list.getEmployer("Prolog")).thenReturn(e1);
        when(mock_list.getProject("Prolog")).thenReturn(ar);
    }

    /**
     * This function is used to test the Employer data
     */
    @Test
    public void getEmployerTest() {
//            KeyResults k1 = new KeyResults();
        List<Employer> op = mock_list.getEmployer("Prolog");
//            System.out.println();
        assertEquals(op, e1);
    }

    /**
     * This function is used to test the project data
     */
    @Test
    public void getProjectTest()
    {
        List<projects>actual= mock_list.getProject("Prolog");
        assertEquals(actual,ar);
    }

    /**
     * This function is used to test the json data
     */
    @Test
    public void TestJSONFunction() {

        EmployerData er=new EmployerData();
        List<projects> ar=new ArrayList<projects>();


        String DummyData = "{\"result\":{\"projects\":[{\"title\":\"JSON Test\",\"owner_id\":5,\"id\":1,\"type\":\"fixed\",\"submitdate\":1647797879,\"preview_description\":\"Hello JSON World\",\"jobs\":[{\"id\":7,\"name\":\"Java\"}]}]}}";
        List<projects> TestList = er.getJSONData(DummyData);
        projects expected_proj = TestList.get(0);

        assertEquals(proj.getId(),expected_proj.getId());
        assertEquals(proj.getOwner_id(),expected_proj.getOwner_id());
        assertEquals(proj.getDesc(),expected_proj.getDesc());
        assertEquals(proj.getEduLevel(),expected_proj.getEduLevel());
        assertEquals(proj.getFleschKincaid(),expected_proj.getFleschKincaid(),0);
        assertEquals(proj.getFleschReadIndex(),expected_proj.getFleschReadIndex(),0);
    }

    /**
     * This method is used to test employer status java file
     */
    @Test
    public void TestEmpStatusFunc(){
        String Data="{\"status\":\"success\",\"result\":{\"id\":61329129,\"username\":\"Andreyshchur3\",\"closed\":false,\"avatar\":\"/img/unknown.png\",\"jobs\":[],\"registration_date\":1647801965,\"limited_account\":false,\"display_name\":\"Andreyshchur3\",\"location\":{\"country\":{\"name\":\"Ukraine\",\"flag_url\":\"/img/flags/png/ua.png\",\"code\":\"ua\",\"highres_flag_url\":\"/img/flags/highres_png/ukraine.png\",\"flag_url_cdn\":\"//cdn3.f-cdn.com/img/flags/png/ua.png\",\"highres_flag_url_cdn\":\"//cdn2.f-cdn.com/img/flags/highres_png/ukraine.png\"}},\"avatar_large\":\"/img/unknown.png\",\"role\":\"employer\",\"chosen_role\":\"employer\",\"status\":{\"payment_verified\":false,\"email_verified\":true,\"deposit_made\":false,\"profile_complete\":false,\"phone_verified\":true,\"identity_verified\":false,\"facebook_connected\":false,\"freelancer_verified_user\":false,\"linkedin_connected\":false},\"avatar_cdn\":\"//cdn6.f-cdn.com/img/unknown.png\",\"avatar_large_cdn\":\"//cdn6.f-cdn.com/img/unknown.png\",\"primary_currency\":{\"id\":1,\"code\":\"USD\",\"sign\":\"$\",\"name\":\"US Dollar\",\"exchange_rate\":1.0,\"country\":\"US\"},\"qualifications\":[],\"primary_language\":\"en\",\"portfolio_count\":0,\"preferred_freelancer\":false,\"public_name\":\"Andreyshchur3\",\"recommendations\":0,\"pool_ids\":[],\"enterprise_ids\":[],\"escrowcom_interaction_required\":false,\"timezone\":{\"id\":282,\"country\":\"SE\",\"timezone\":\"Europe/Stockholm\",\"offset\":1.0},\"responsiveness\":5.0,\"oauth_password_credentials_allowed\":false,\"registration_completed\":true,\"is_profile_visible\":true,\"operating_areas\":[]},\"request_id\":\"c4581a607fdd2fe36fdfc6d461dc9ff6\"}";
        Emp_Status testobj=new Emp_Status("false","true","no","yes","yes","yes","yes","yes","yes");
        EmployerData e1=new EmployerData();
        Emp_Status compareobj=e1.getStatusData(Data);
        assertEquals(testobj.isPayment_verified(),compareobj.isPayment_verified());
        assertEquals(testobj.isEmail_verified(),compareobj.isEmail_verified());
    }
}


