package controllers;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.UnsupportedEncodingException;

public class RouteControllerTest {
    @Test
    public void testEncode() throws UnsupportedEncodingException {
        RouteController r1=new RouteController();
        String temp=r1.EncodeURL("C# Programming");
        String temp1=r1.EncodeURL("UI/UXProgramming");
        assertNotEquals("skill/C%23%20Programming",temp);
//        assertEquals("skill/UI%2FUXProgramming",temp1);

    }
}
