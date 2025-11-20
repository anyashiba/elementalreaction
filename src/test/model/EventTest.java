package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e1;
    private Date d1;
	
	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.
    
    @BeforeEach
	public void runBefore() {
        e1 = new Event("Checked team");   // (1)
        d1 = Calendar.getInstance().getTime();   // (2)
    }
    
    @Test
	public void testEvent() {
        Event e2 = new Event("Checked team");
        assertEquals("Checked team", e1.getDescription());
        assertTrue(e1.equals(e1));
        assertFalse(e1.equals(e2));
        assertFalse(e1.equals(null));
    }
    
    @Test
	public void testToString() {
        assertEquals(d1.toString() + "\n" + "Checked team", e1.toString());
    }
}
