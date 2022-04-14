package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import junit.framework.TestCase;

import static org.junit.Assert.assertEquals;

public class EmployerDataTest extends TestCase {
    /**
     * Actors System
     */
    static ActorSystem system;
    public void setUp() throws Exception {
        system = ActorSystem.create();
        system.actorOf(SkillsResult.getProps());
    }

    public void tearDown() throws Exception {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    public void testGetData() {

        String data = EmployerData.getData("key",null);
        assertEquals("NA",data);

    }

    public void testGetStatusData() {
    }

    public void testGetEmployer() {
        new TestKit(system) {{
            final Props props = EmployerData.getProps(); //Props.create(EmployerData.class);
            final ActorRef tsa = system.actorOf(props);
            final TestKit probe1 = new TestKit(system);
            tsa.tell("7194873",probe1.getRef());

        }};
    }

    public void testGetProject() {
//        new TestKit(system) {{
//            final Props props = Props.create(EmployerData.class);
//            final ActorRef tsa = system.actorOf(props);
//            final TestKit probe1 = new TestKit(system);
//            tsa.tell("htp://",probe1.getRef());
//
//        }};
    }

    public void testGetJSONData() {
    }
}