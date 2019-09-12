import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void testGetAppointment(){
        assertEquals("12th of December 2019,03:00 PM", new Event("testing","12/12/2019 1500").getAppointment());
    }

    @Test
    public void testGetAt() {
        assertEquals("12/12/2019 1500", new Event("testing", "12/12/2019 1500").getAt());
    }

    @Test
    public void testGetDescription(){
        assertEquals("testing", new Event("testing", "12/12/2019 1500").getDescription());
    }

    @Test
    public void testDone() {
        Event testCase = new Event("testing", "12/12/2019 1500");
        testCase.setDone();
        assertEquals(true, testCase.getDone());
    }

    @Test
    public void testGetType() {
            Event testCase = new Event("testing", "12/12/2019 1500");
            assertEquals(Task.TypeClass.E, testCase.getType());
    }

}
