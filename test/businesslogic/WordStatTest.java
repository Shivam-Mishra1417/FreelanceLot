package businesslogic;

import models.Stats;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This class is used to test the Word Statistic
 * @author Yagnik Pansuriya
 */

public class WordStatTest {
    private static Map<String,Long> individualSorted=new HashMap<>();
    private static Map<String,Long> globalSorted=new HashMap<>();
    private static List<String> previewDesc =new ArrayList<>();
    private static WordStats ws=new WordStats();
    private static Stats s1=new Stats("1","Java Coding Part","Hello Yagnik",individualSorted,null);

    /**
     * This method is used to setup the testing data
     */
    @BeforeClass
    public static void setup(){
        individualSorted.put("Hello",1L);
        individualSorted.put("Yagnik",1L);

        globalSorted.put("Hello",1L);
        globalSorted.put("Yagnik",1L);

        previewDesc.add("Yagnik");
        previewDesc.add("Hello");
    }

    /**
     * This method is used to test <i>individualWordStat</i> method of Stats Class
     */
    @Test
    public void individualWordStat(){
        assertEquals(individualSorted,ws.wordStat("Hello Yagnik"));
    }

    /**
     * This method is used to test the <i>globalWordStat</i> method of Stats CLass
     */
    @Test
    public void globalWordStat(){
        assertEquals(globalSorted,ws.globalWordStat(previewDesc));
    }

    /**
     * This method is used to test the getter method for individual stats
     */
    @Test
    public void getIndividualSorted(){
     WordStats w2=new WordStats();
        Map<String,Long> W3=new HashMap<>();
        W3.put("Hello",1L);
        W3.put("Yagnik",1L);
        Stats ws1=new Stats("1","Java Coding Part","Hello Yagnik",W3,null);
        assertEquals(ws1.getIndividual_sorted(),w2.getWordstat("1","Java Coding Part","Hello Yagnik").getIndividual_sorted());

    }

    /**
     * This method is used to test the getter method for global stats
     */
    @Test
    public void getGlobalSorted(){
        WordStats w2=new WordStats();
        Map<String,Long> W3=new HashMap<>();
        W3.put("Hello",1L);
        W3.put("Yagnik",1L);
        Stats ws1=new Stats("1","Java Coding Part","Hello Yagnik",null,W3);
        assertNotEquals(ws1.getGlobal_sorted(),w2.getGlobalWordstat().getGlobal_sorted());

    }
}
