package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
//import org.scalatest.junit.JUnitSuite;

//import actors.IndividualReadabilityActor;
// import services.FreeLancerService;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
// import util.MockObjectCreator;

/**
 * Tests the functionality of IndividualReadabilityActor.
 *
 * @author Anurag Shekhar
 */
public class WebsocketActorTest{//} extends JUnitSuite {

    /**
     * Actors System
     */
    private static ActorSystem testSystem;

    /**
     * Mock of FreeLancerService
     */
    static private WebsocketActor ws_actor = mock(WebsocketActor.class);

    ActorRef out;
    /**
     * Initializes objects needed for tests before each unit test
     */
    @BeforeClass
    public static void setup() {
        testSystem = ActorSystem.create();
        testSystem.actorOf(WebsocketActor.props());
    }

    /**
     * Teardown Actor system after all tests
     */
    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(testSystem);
        testSystem = null;
    }

    /**
     * Sends individual Readability
     */
    @Test
    public void testIndividualReadabilityActor() {
        new TestKit(testSystem) {{
//            final Props props = Props.create(WebsocketActor.class);
            final Props props=WebsocketActor.props(out);
            final ActorRef tsa = testSystem.actorOf(props);
            final TestKit probe1 = new TestKit(testSystem);

//            tsa.tell("java", probe1.getRef());
//            tsa.tell("sql", probe1.getRef());
            tsa.tell("test", probe1.getRef());
//            tsa.tell("java", probe1.getRef());
            //tsa.tell("sql", probe1.getRef());
            //tsa.tell((Integer)2, probe1.getRef());
//            tsa.tell(null,probe1.getRef());
            //tsa.tell("/",probe1.getRef());

        }};
    }

    @Test
    public void maptest(){
        //KeyResults k1=new KeyResults();
        HashMap<String, Integer> map = new HashMap<>();
        WebsocketActor.addToSkillMap("Java",7);
        int compare=WebsocketActor.getToSkillMap("Java");
        assertEquals(7,compare);
    }
//    public void testGetData() {
//
//        String data = String.valueOf(WebsocketActor.getData("java"));
//        assertEquals("NA",data);
//
//    }
}