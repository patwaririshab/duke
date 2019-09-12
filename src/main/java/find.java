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
            TaskList tempTaskList = new TaskList(searchResult);
            tempTaskList.viewList();
        }
    }

    public find(TaskList tasks, String keyword) {
            this.keyword = keyword;
            this.tasklist = tasks.getTaskList();
    }
    public static void searchTaskList(TaskList taskList, String searchdescription) {
        String[] searchwords = searchdescription.split(" ", 0); //Creates a iterable array of search words
        ArrayList<Todo> returnList = new ArrayList<>();

        //For each task in taskList, check if it matches any search word
        for (Todo task:taskList.getTaskList()){
            ArrayList<String> taskwords = new ArrayList<>();
            //Add all the words of the task description to the taskwords
            taskwords.addAll(Arrays.asList(task.getDescription().split(" ",0)));
            switch (task.getType()){
                case D:
                    taskwords.addAll(Arrays.asList(((Deadline) task).getAppointment().split(" ")));
                    taskwords.addAll(Arrays.asList(((Deadline) task).getBy().split(" ")));
                    break;
                case E:
                    taskwords.addAll(Arrays.asList(((Event) task).getAppointment().split(" ")));
                    taskwords.addAll(Arrays.asList(((Event) task).getAt().split(" ")));
                    break;
                default:
                    break;
            }

            // Iterate through searchwords and taskwords and check if any words match, if so break and add to return list
            boolean flag = false;
            for(String word:searchwords){
                if (flag) {break;}
                for(String taskword:taskwords){
                    if(word.equals(taskword)){
                        returnList.add(task);
                        flag = true;
                        break;
                    }
                }
            }
        }
        showSearchResult(returnList);
    }
}
