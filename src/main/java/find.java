import java.util.ArrayList;
import java.util.Arrays;

/**
 * Find class obtains the keywords entered by the user, and then looks through the tasklist
 * first searching all the descriptions, and if have, searching all the appointments. It prints
 * the search results.
 */
public class find {
    private String keyword;
    private ArrayList<Todo> tasklist;

    private static void showSearchResult(ArrayList<Todo> searchResult) {
        if(searchResult.size() == 0) {
            System.out.println("No task matching query was found, please refine your search!\n");
        } else {
            System.out.print(Duke.line + "  Here are the tasks in your list:\n");

            TaskList.viewList(searchResult);
        }
    }

    public find(TaskList tasks, String keyword) {
            this.keyword = keyword;
            this.tasklist = tasks.getTaskList();
    }

    public static void searchTaskList(TaskList taskList, String searchdescription) {
        String[] searchwords = searchdescription.split(" ", 0); //Creates a iterable array of search words

        //For each task in taskList, check if it matches any search word
        for (Task task:taskList.getTaskList()){
            ArrayList<String> taskwords = new ArrayList<>();
            //Add all the words of the task description to the taskwords
            taskwords.addAll(Arrays.asList(task.getDescription().split(" ",0)));
            switch (((Todo) task).getType()){
                case D:
                    taskwords.addAll(Arrays.asList(((Deadline) task).getAppointment().split(" ")));
                    break;
                case E:
                    taskwords.addAll(Arrays.asList(((Event) task).getAppointment().split(" ")));
                    break;
                default:
                    break;
            }

//            boolean flag = false;
//            for()
//                if(flag == true){ break;}



        }



    }



    public static void searchList(ArrayList<Todo> tasklist, String keyword){
        // Get all the descriptions

        String[] splitKeywords = keyword.split(" ", 0);

       ArrayList<Todo> printlist = new ArrayList<>();


        //For entries in tasklist
        for(Todo a:tasklist) {
            //Split each entry's description into keywords
            String[] splitTaskKeywords = a.getDescription().split(" ", 0);
            String[] words = new String[0];
                switch (a.getType()){
                    case D:
                        words = ((Deadline) a).getAppointment().split(" ");
                        break;
                    case E:
                        words = ((Event) a).getAppointment().split(" ");
                        break;
                    default:
                        break;
            }

            boolean flag = false;
            
            for(String b:splitKeywords){
                if (flag == true ) break;
                for (String c:splitTaskKeywords) {
                    if(b.equals(c)){
                        printlist.add(a);
                        flag = true;
                        break;
                    }
                }
                if (!(a.getType() == Todo.TypeClass.T)) {
                    if (flag == true) break;
                    //Check if the appointment is in keyword
                    for(String d:words) {
                        if(b.equals(d)) {
                            printlist.add(a);
                            flag = true;
                            break;
                        }
                    }
                }
            }
        }
        showSearchResult(printlist);
    }
}
