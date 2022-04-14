package actors;

import actors.WebsocketActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class FleschReadabiltyTest {
    /**
     * Actors System
     */
    private static ActorSystem testSystem;

    /**
     * Mock of FreeLancerService
     */
    static private FleschReadabilty fr_actor = mock(FleschReadabilty.class);

    /**
     * Initializes objects needed for tests before each unit test
     */


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        testSystem = ActorSystem.create();
        testSystem.actorOf(FleschReadabilty.props());
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        TestKit.shutdownActorSystem(testSystem);
        testSystem = null;
    }

    @org.junit.jupiter.api.Test
    void props() {
        new TestKit(testSystem) {{
            final Props props = Props.create(FleschReadabilty.class);
            final ActorRef tsa = testSystem.actorOf(props);
            final TestKit probe1 = new TestKit(testSystem);
            tsa.tell("Java formerly called as Oak", probe1.getRef());
//            tsa.tell("sql", probe1.getRef());
//            tsa.tell("java", probe1.getRef());
//            tsa.tell("sql", probe1.getRef());
//            tsa.tell((Integer)2, probe1.getRef());
//            tsa.tell(null,probe1.getRef());
        }};
    }

    @org.junit.jupiter.api.Test
    void createReceive() {
    }

    @org.junit.jupiter.api.Test
    public void eduLevelTest(){
        new TestKit(testSystem) {{
            final Props props = Props.create(FleschReadabilty.class);
            final ActorRef tsa = testSystem.actorOf(props);
            final TestKit probe1 = new TestKit(testSystem);
            tsa.tell((Double)101.0, probe1.getRef());
            tsa.tell((Double)95.0, probe1.getRef());
            tsa.tell((Double)85.0, probe1.getRef());
            tsa.tell((Double)75.0, probe1.getRef());
            tsa.tell((Double)67.0, probe1.getRef());
            tsa.tell((Double)65.0, probe1.getRef());
            tsa.tell((Double)63.0, probe1.getRef());
            tsa.tell((Double)55.0, probe1.getRef());
            tsa.tell((Double)31.0, probe1.getRef());
            tsa.tell((Double)29.0, probe1.getRef());
            Double dtest = -20.0;
            tsa.tell(dtest, probe1.getRef());

        }};
//        assertEquals("Early",fr.getEduLevel(101));
//        assertEquals("5th Grade",fr.getEduLevel(95));
//        assertEquals("6th Grade",fr.getEduLevel(85));
//        assertEquals("7th Grade",fr.getEduLevel(75));
//        assertEquals("8th Grade",fr.getEduLevel(68));
//        assertEquals("9th Grade",fr.getEduLevel(63));
//        assertEquals("High School",fr.getEduLevel(55));
//        assertEquals("some college",fr.getEduLevel(31));
//        assertEquals("college graduate",fr.getEduLevel(29));
//        assertEquals("Law School Graduate",fr.getEduLevel(-1));
    }

    @org.junit.jupiter.api.Test
    void flesch_read() {
    }

    @org.junit.jupiter.api.Test
    void flesch_kincaid() {
    }

    @org.junit.jupiter.api.Test
    void countSentences() {
    }

    @org.junit.jupiter.api.Test
    void wordCount() {
    }

    @org.junit.jupiter.api.Test
    void countSyllables() {
    }
}