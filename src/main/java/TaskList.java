import java.util.ArrayList;
import java.util.Vector;

public class TaskList {
    private ArrayList<Todo> TaskList;

    public void viewList(){
        for(int i = 0; i < TaskList.size(); ++i){
            System.out.print("  " + (i+1) + ". [" + TaskList.get(i).getType() + "] ["
                    + TaskList.get(i).isDone() + "]  " + TaskList.get(i).getDescription());
            switch (TaskList.get(i).getType()){
                case T:
                    System.out.println();
                    break;
                case D:
                    System.out.println(" (by: " + ((Deadline) TaskList.get(i)).getAppointment() + ")");
                    break;
                case E:
                    System.out.println(" (at: " + ((Event) TaskList.get(i)).getAppointment() + ")");
                    break;
            }
        }
        System.out.print(Duke.line);
    }
    public void updateDone(String words) {
        int itemNo = Integer.parseInt(words);
        TaskList.get(itemNo - 1).setDone();
        System.out.println(Duke.line
                + "Nice! I've marked this task as done:\n"
                + "  [" + TaskList.get(itemNo -1).isDone() + "] " + TaskList.get(itemNo - 1).getDescription() + "\n"
                + Duke.line);
    }
    public void addToDo( String description, String[] words) {
        try {
            String test_input = words[1]; //try to purposely access second element of the array to check for input
            Todo tempTask = new Todo(description);
            TaskList.add(tempTask);
            System.out.println(Duke.line + "  Got it. I've added this task:\n"
                    + "    [T] [" + tempTask.isDone() + "]  " // to add type
                    + tempTask.getDescription() + "\n"
                    + "  Now you have " + TaskList.size() + " tasks in the list.\n"
                    + Duke.line);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Duke.line + " \u2639 OOPS!!! The description of a todo cannot be empty.\n" + Duke.line);
        }
    }
    public void addSpecial(String description, String[] words, String keyword){
        try {
            int byIndex = 0;
            for (int i = 1; i < words.length; ++i) {
                if (words[i].equals("/" + keyword)) {
                    byIndex = i;
                    break;
                }
            }
            if (byIndex != 0) {
                Vector<String> sb = new Vector<String>(50);
                for (int i = byIndex + 1; i < words.length; ++i) {
                    sb.add(words[i]);
                }
                String resultantString = String.join(" ", sb);
                Todo newTodo;
                switch (keyword) {
                    case ("by"):
                        newTodo = new Deadline(description, resultantString);
                        TaskList.add(newTodo);
                        System.out.println(Duke.line + "  Got it. I've added this task:\n"
                                + "    [" + newTodo.getType() + "] [" + newTodo.isDone() + "]  " // to add type
                                + newTodo.getDescription() + " (" + keyword + ": "
                                + ((Deadline) newTodo).getAppointment() + ")\n"
                                + "  Now you have " + TaskList.size() + " tasks in the list.\n"
                                + Duke.line);
                        break;
                    case ("at"):
                        newTodo = new Event(description, resultantString);
                        TaskList.add(newTodo);
                        System.out.println(Duke.line + "  Got it. I've added this task:\n"
                                + "    [" + newTodo.getType() + "] [" + newTodo.isDone() + "]  " // to add type
                                + newTodo.getDescription() + " (" + keyword + ": "
                                + ((Event) newTodo).getAppointment() + ")\n"
                                + "  Now you have " + TaskList.size() + " tasks in the list.\n"
                                + Duke.line);
                }


            }
        } catch (Exception e) {
            System.out.println(Duke.line + "  You have entered an invalid input, ensure that the deadline or appointment is of the form:\n" +
                    "  DD/MM/YY HHmm\n" + Duke.line);
        }
    }
    public void deleteTask(String deleteIndex) {
        try {
            int delete = Integer.parseInt(deleteIndex);
            Todo temp = TaskList.get(delete - 1);
            TaskList.remove((delete - 1));

            String temptextA = (Duke.line + "  Noted. I have removed this task:\n" + "    [" + temp.getType() + "] ["+ temp.isDone() + "] " + temp.getDescription());
            String temptextB = String.format("  Now you have %d tasks in the list.", TaskList.size()) + "\n" + Duke.line;

            switch (temp.getType()) {
                case T:
                    System.out.println(temptextA);
                    System.out.print(temptextB);
                    break;
                case D:
                    System.out.println(temptextA + " (by: " + ((Deadline) temp).getAppointment() + ")");
                    System.out.print(temptextB);
                    break;
                case E:
                    System.out.println(temptextA + " (at: " + ((Event) temp).getAppointment() + ")");
                    System.out.print(temptextB);
                    break;
            }
        } catch (Exception e) {
            System.out.println(Duke.line + "  Unable to delete task, could you double check the index of the task you want to delete?\n" + Duke.line);
        }
    }
    public ArrayList<Todo> getTaskList() {
        return TaskList;
    }

    public TaskList() {
        this.TaskList = new ArrayList<Todo>();
    }
    public TaskList(ArrayList<Todo> itemsList) {
        this.TaskList = itemsList;
    }
}
