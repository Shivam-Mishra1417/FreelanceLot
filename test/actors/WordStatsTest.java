package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class WordStatsTest {
    /**
     * Actors System
     */
    private static ActorSystem testSystem;

    /**
     * Mock of FreeLancerService
     */
    static private WordStats fr_actor = mock(WordStats.class);

    /**
     * Initializes objects needed for tests before each unit test
     */
    @BeforeAll
    static void setUp() {
        testSystem = ActorSystem.create();
        testSystem.actorOf(WordStats.getProps());
    }

    @AfterAll
    static void tearDown() {
        TestKit.shutdownActorSystem(testSystem);
        testSystem = null;
    }
    @Test
    void getProps() {
        new TestKit(testSystem) {{
            final Props props = Props.create(WordStats.class);
            final ActorRef tsa = testSystem.actorOf(props);
            final TestKit probe1 = new TestKit(testSystem);
            tsa.tell("Java formerly called as Oak", probe1.getRef());
            //tsa.tell(1, probe1.getRef());
    }};
    }

    @Test
    void createReceive() {
    }

    @Test
    void wordStat() {
    }

    @Ignore
    void getWordstat() {
        new TestKit(testSystem) {{
            final Props props = Props.create(WordStats.class);
            final ActorRef tsa = testSystem.actorOf(props);
            final TestKit probe1 = new TestKit(testSystem);
            tsa.tell("Java formerly called as Oak", probe1.getRef());
//            Integer i = 1;
//            tsa.tell(i, probe1.getRef());
            //tsa.tell(1.0, probe1.getRef());
        }};
    }

    @Test
    void globalWordStat() {
    }

    @Test
    void getGlobalWordstat() {
        new TestKit(testSystem) {{
            final Props props1 = Props.create(WordStats.class);
            final ActorRef tsa1 = testSystem.actorOf(props1);
            final TestKit probe2 = new TestKit(testSystem);
            int j = 1;
            tsa1.tell(j, probe2.getRef());


        }};
    }
}