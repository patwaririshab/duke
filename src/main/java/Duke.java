import java.util.*;

public class Duke {
     public static String line = "____________________________________________\n";
     private static void addWord(String word, List<Todo> wordList) {
         Todo tempTask = new Todo(word);
        wordList.add(tempTask);
        System.out.print(line + "  added: "+ word + '\n' + line);
    }

    private static void viewList(List<Todo> TaskList){
        System.out.print(line);
        for(int i = 0; i < TaskList.size(); ++i){
            System.out.println((i+1) + ". [" + TaskList.get(i).getType() + "] ["
                    + TaskList.get(i).isDone() + "]  " + TaskList.get(i).getDescription());
        }
        System.out.print(line);
    }

    private static void updateDone(List<Todo> itemslist , String words) {
        int itemNo = Integer.parseInt(words);
        System.out.println("'" + words+"'");
         itemslist.get(itemNo - 1).setDone();
        System.out.println(line
                + "Nice! I've marked this task as done:\n"
                + "  [" + itemslist.get(itemNo -1).isDone() + "] " + itemslist.get(itemNo - 1).getDescription() + "\n"
                + line);
    }

    private static void addToDo(List<Todo> itemslist , String description) {
         Todo tempTask = new Todo(description);
         itemslist.add(tempTask);
         System.out.println(line + "  Got it. I've added this task:\n"
                                 + "  [T] [" + tempTask.isDone() + "]  " // to add type
                                 + tempTask.getDescription() + "\n" + line);

    } //to change print for type

    private static String getCommandWord(String indes){
        String[] words = indes.split("\\s",0); // splits the string based on whitespace
        return words[0];
     }

     private static String getDescription(String indes, String type) {
         String[] words = indes.split("\\s", 0); //splitting input based on whitespaces

         switch(type){
             case ("done"):
                 return words[1];
             case ("todo"):
                 StringJoiner sj = new StringJoiner(" ");
                 for (int i = 1; i < words.length; ++i) {
                     sj.add(words[i]);
                 }
                 return sj.toString();
             case ("deadline"):
                 for(int i = 1; i < indes.length(); ++i) {
                     if (words[i].equals("/by")){
                         String[] wordlist = new String[i-1];
                         for(int j = 1; j < i; j++){
                             wordlist[j-1] = words[j];
                         }
                         return String.join(" ", wordlist);
                     }
                 }
                 break;
             case ("event"):
                 for(int i = 1; i < indes.length(); ++i) {
                     if (words[i].equals("/at")){
                         String[] wordlist = new String[i-1];
                         for(int j = 1; j < i; j++){
                             wordlist[j-1] = words[j];
                         }
                         return String.join(" ", wordlist);
                     }
                 }
                 break;
             default:
                 break;
         }
         return null;
     }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println(logo
                + line
                + "  Hello I'm Duke!\n"
                + "  What can I do for you?\n"
                + line);


        Scanner input = new Scanner(System.in); // Simplify call to read input
        List<Todo> itemslist = new ArrayList<>(); // Creating an ArrayList of String
        String indes = input.nextLine(); // Reading the whole input description

        while (!indes.equals("bye")) {
            switch(getCommandWord(indes)) {
                case ("list"):
                    viewList(itemslist);
                    break;
                case ("done"):
                    updateDone(itemslist, getDescription(indes, "done"));
                    break;
                case ("todo"):
                    addToDo(itemslist, getDescription(indes, "todo"));
                    break;
                case ("deadline"):
//                    addDeadline(itemslist, getDescription(indes, "deadline"));
                    break;

                default:
                    addWord(indes, itemslist);
                    break;
            }
                indes = input.nextLine();
        }
            System.out.print( line + "  Bye. Hope to see you again soon!\n" + line);
            System.exit(0);
    }
}

