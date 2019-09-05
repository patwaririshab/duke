import java.util.ArrayList;

public class find extends Duke{
    private String keyword;
    private ArrayList<Todo> tasklist;


    private static void showSearchResult(ArrayList<Todo> searchResult) {
        if(searchResult.size() == 0) {
            System.out.println("No task matching query was found, please refine your search!\n");
        } else {
            System.out.print(line + "  Here are the tasks in your list:\n");
            Duke.viewList(searchResult);
        }
    }

    public  find( ArrayList<Todo> tasklist, String keyword) {
            this.keyword = keyword;
            this.tasklist = tasklist;
    }
    public static void searchList(ArrayList<Todo> tasklist, String keyword){
        // Get all the descriptions
        String[] splitKeywords = keyword.split(" ", 0);
        ArrayList<Todo> printlist = new ArrayList<>();
        
        for(Todo a:tasklist) {
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
