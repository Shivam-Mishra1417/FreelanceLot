package businesslogic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class method is used to Test the flesch readabilty Index
 * @author Yash Patel
 */
public class FleschReadabilityTest {
    private static FleschReadabilty fr=new FleschReadabilty();
    /**
     * This method is used to test Education level.
     */
    @Test
    public void eduLevelTest(){
        assertEquals("Early",fr.getEduLevel(101));
        assertEquals("5th Grade",fr.getEduLevel(95));
        assertEquals("6th Grade",fr.getEduLevel(85));
        assertEquals("7th Grade",fr.getEduLevel(75));
        assertEquals("8th Grade",fr.getEduLevel(68));
        assertEquals("9th Grade",fr.getEduLevel(63));
        assertEquals("High School",fr.getEduLevel(55));
        assertEquals("some college",fr.getEduLevel(31));
        assertEquals("college graduate",fr.getEduLevel(29));
        assertEquals("Law School Graduate",fr.getEduLevel(-1));
    }

    /**
     * This method is used to test flesch readbilty Index method
     */
    @Test
    public void FleschReadTest(){
        assertEquals(97.05386363636366d,fr.flesch_read("My Name is Yash Shah.And I am Rano Rana Ni Rite"),0);
    }

    /**
     * This Method is used to test the flesch kincaid method
     */
    @Test
    public void FleschKincaid(){
        assertEquals(2.6459090909090897,fr.flesch_kincaid("My Name is Yash Shah.And I am Rano Rana Ni Rite"),0);
    }


}
