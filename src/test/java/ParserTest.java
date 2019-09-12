import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    TaskList testTaskList = new TaskList();

    @Test
    public void testGetCommandWord() {
        new Parser().processInput(testTaskList, "todo testthisapp");
        assertEquals(Task.TypeClass.T, testTaskList.getTaskList().get(0).getType());

        new Parser().processInput(testTaskList, "deadline testthisapp /by 12/12/2019 1500");
        assertEquals(Task.TypeClass.D, testTaskList.getTaskList().get(1).getType());

        new Parser().processInput(testTaskList, "event testthisapp /at 12/12/2019 1500");
        assertEquals(Task.TypeClass.E, testTaskList.getTaskList().get(2).getType());
    }

    @Test
    public void getCommandWord_Exception_Test() {
        try{
            new Parser().processInput(testTaskList, "dingdong abc");
        } catch (Exception e) {
            System.out.println(Duke.line + "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n" + Duke.line);
        }
    }
}


