package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import models.projects;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SkillsResultTest {
    /**
     * Actors System
     */
    static ActorSystem system;
    /**
     * Mock of the Websocket
     */
    static private SkillsResult skill_mock = mock(SkillsResult.class);
    private static projects proj;
    private static ArrayList<String> skills = new ArrayList<>();
    List<projects> ar = new ArrayList<projects>();
    ArrayList<String> skillslist = new ArrayList<>();
    @BeforeEach
    void setUp() throws ParseException {
        skills.add("Java");
        proj = new projects(1, 5, "JSON Test", "Hello JSON World", skills, 97.08, 1.31, "5th Grade","13-12-2020","fixed");
        skillslist.add("Rahul");
        skillslist.add("Shivam");
        ar.add(new projects(1417, 1417, "Test_Freelancelot", "Testing Freelancelot", skillslist, 14.17, 14.17, "Early", "20 Mar 2022", "Fixed"));
        when(skill_mock.getSkills("Prolog")).thenReturn(ar);

        system = ActorSystem.create();
        system.actorOf(SkillsResult.getProps());
    }

    @AfterEach
    void tearDown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }


    @Test
    void getJSONData() throws ParseException {
        String DummyData = "{\"result\":{\"projects\":[{\"title\":\"JSON Test\",\"owner_id\":5,\"id\":1,\"type\":\"fixed\",\"submitdate\":1647797879,\"preview_description\":\"Hello JSON World\",\"jobs\":[{\"id\":7,\"name\":\"Java\"}]}]}}";
        // SkillsResult s1=new SkillsResult();
        List<projects> p1=SkillsResult.getJSONData(DummyData);
        projects expected=p1.get(0);
        assertEquals(proj.getId(),expected.getId());
    }

    @Test
    void getSkills() {
        new TestKit(system) {{
            final Props props = Props.create(SkillsResult.class);
            final ActorRef tsa = system.actorOf(props);
            final TestKit probe1 = new TestKit(system);
            tsa.tell("test",probe1.getRef());
        }};
    }
}