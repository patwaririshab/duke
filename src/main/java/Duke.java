import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Duke {
    public static String line = "________________________________________________________\n";
//    private static void addWord(String word, List<Todo> wordList) {
//         Todo tempTask = new Todo(word);
//        wordList.add(tempTask);
//        System.out.print(line + "  added: "+ word + '\n' + line);
//    }
    private static void viewList(ArrayList<Todo> TaskList){
        System.out.print(line + "  Here are the tasks in your list:\n");
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
        System.out.print(line);
    }
    private static void updateDone(ArrayList<Todo> itemslist , String words) {
        int itemNo = Integer.parseInt(words);
//        System.out.println("'" + words+"'");
         itemslist.get(itemNo - 1).setDone();
        System.out.println(line
                + "Nice! I've marked this task as done:\n"
                + "  [" + itemslist.get(itemNo -1).isDone() + "] " + itemslist.get(itemNo - 1).getDescription() + "\n"
                + line);
    }
    private static void addToDo(ArrayList<Todo> itemslist , String description, String[] words) {
            try {
                String test_input = words[1]; //try to purposely access second element of the array to check for input
                Todo tempTask = new Todo(description);
                itemslist.add(tempTask);
                System.out.println(line + "  Got it. I've added this task:\n"
                        + "    [T] [" + tempTask.isDone() + "]  " // to add type
                        + tempTask.getDescription() + "\n"
                        + "  Now you have " + itemslist.size() + " tasks in the list.\n"
                        + line);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(line + " \u2639 OOPS!!! The description of a todo cannot be empty.\n" + line);
            }
    }
    private static String getCommandWord(String indes) throws InvalidInputException {
        String[] words = indes.split("\\s",0); // splits the string based on whitespace
        if (words[0].equals("todo") || words[0].equals("done") ||words[0].equals("list") ||words[0].equals("event") || words[0].equals("deadline") || words[0].equals("delete"))
            return words[0];
        else
            throw new InvalidInputException(words[0]);
     }
    private static String getDescription(String[] words, String type) {
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
                 for(int i = 1; i < words.length; ++i) {
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
                 for(int i = 1; i < words.length; ++i) {
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
    private static void addSpecial(ArrayList<Todo> TaskList, String description, String[] words, String keyword){

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
                        System.out.println(line + "  Got it. I've added this task:\n"
                                + "    [" + newTodo.getType() + "] [" + newTodo.isDone() + "]  " // to add type
                                + newTodo.getDescription() + " (" + keyword + ": "
                                + ((Deadline) newTodo).getAppointment() + ")\n"
                                + "  Now you have " + TaskList.size() + " tasks in the list.\n"
                                + line);
                        break;
                    case ("at"):
                        newTodo = new Event(description, resultantString);
                        TaskList.add(newTodo);
                        System.out.println(line + "  Got it. I've added this task:\n"
                                + "    [" + newTodo.getType() + "] [" + newTodo.isDone() + "]  " // to add type
                                + newTodo.getDescription() + " (" + keyword + ": "
                                + ((Event) newTodo).getAppointment() + ")\n"
                                + "  Now you have " + TaskList.size() + " tasks in the list.\n"
                                + line);
                }


            }
        } catch (Exception e) {
            System.out.println(line + "  You have entered an invalid input, ensure that the deadline or appointment is of the form:\n" +
                                "  DD/MM/YY HHmm\n" + line);
        }
    }
    private static void loadFile(String filePath, ArrayList<Todo> itemslist) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for(String w:lines) {
                String[] splitline = w.split(" " + "\\|" + " ", 0);
                switch (splitline[0]) {
                    case("T"):
                        Todo temp = new Todo(splitline[2]);
                        if (splitline[1].equals("1")) temp.setDone();
                        itemslist.add(temp);
                        break;
                    case("D"):
                        Deadline temp2 = new Deadline(splitline[2],splitline[3]);
                        if (splitline[1].equals("1")) temp2.setDone();
                        itemslist.add(temp2);
                        break;
                    case("E"):
                        Event temp3 = new Event(splitline[2],splitline[3]);
                        if (splitline[1].equals("1")) temp3.setDone();
                        itemslist.add(temp3);
                        break;
                    default:
                        break;
                }
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filePath + "'");
        }
        catch (IOException ex) {
            System.out.println("Error reading file '" + filePath + "'");
        }
    }
    private static void writeToFile(FileWriter fw, ArrayList<Todo> itemslist) throws IOException {
        for(Todo item:itemslist){
            switch(item.getType()){
                case T:
                    fw.write("T | " + (item.isDone().equals(true) ? "1" : "0") + " | " + item.getDescription() +'\n');
                    break;
                case D:
                    fw.write("D | " + (item.isDone().equals(true) ? "1" : "0") + " | " + item.getDescription() + " | " + ((Deadline) item).getBy() + "\n");
                    break;
                case E:
                    fw.write("E | " + (item.isDone().equals(true) ? "1" : "0") + " | " + item.getDescription() + " | " + ((Event) item).getAt() + "\n");
                    break;
            }
        }
    }
    private static void deleteTask(ArrayList<Todo> TaskList, String deleteIndex){
        try {
            int delete = Integer.parseInt(deleteIndex);
            Todo temp = TaskList.get(delete - 1);
            TaskList.remove((delete - 1));
            String temptext = String.format("  Now you have %d tasks in the list.", TaskList.size()) + "\n" + line;
            switch (temp.getType()) {
                case T:
                    System.out.println(line + "  Noted. I have removed this task:\n" + "    [" + temp.getType() + "] " + "[" + temp.isDone() + "] " + temp.getDescription());
                    System.out.print(temptext);
                    break;
                case D:
                    System.out.println(line + "  Noted. I have removed this task:\n" + "    [" + temp.getType() + "] " + "[" + temp.isDone() + "] " + temp.getDescription()
                            + " (by: " + ((Deadline) temp).getAppointment() + ")");
                    System.out.print(temptext);
                    break;
                case E:
                    System.out.println(line + "  Noted. I have removed this task:\n" + "    [" + temp.getType() + "] " + "[" + temp.isDone() + "] " + temp.getDescription()
                            + " (at: " + ((Event) temp).getAppointment() + ")");
                    System.out.print(temptext);
                    break;
            }
        } catch (Exception e) {
            System.out.println(line + "  Unable to delete task, could you double check the index of the task you want to delete?\n" + line);
        }
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
        ArrayList<Todo> itemslist = new ArrayList<>(); // Creating an arraylist of todo class

        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + "/src/main/java/task.txt";
        loadFile(filePath, itemslist); // Load existing list from persistent storage and update itemslist

        String indes = input.nextLine(); // Reading the whole input description
        while (!indes.equals("bye")) {
            String[] words = indes.split("\\s", 0); //splitting input based on whitespaces
            try {
                switch (getCommandWord(indes)) {
                    case ("list"):
                        viewList(itemslist);
                        break;
                    case ("done"):
                        updateDone(itemslist, getDescription(words, "done"));
                        break;
                    case ("todo"):
                        addToDo(itemslist, getDescription(words, "todo"), words);
                        break;
                    case ("deadline"):
                        addSpecial(itemslist, getDescription(words, "deadline"), words, "by");
                        break;
                    case ("event"):
                        addSpecial(itemslist, getDescription(words, "event"), words, "at");
                        break;
                    case ("delete"):
                        deleteTask(itemslist, getDescription(words, "done"));
                        break;
//                default:
////                    addWord(indes, itemslist);
//                    System.out.println(line + "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n" + line );
//                    break;
                }
            } catch (InvalidInputException e) {
                      System.out.println(line + "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n" + line );
            }

                indes = input.nextLine();
        }

            // Updating persistent storage
            try {
                File fold = new File(filePath);
                fold.delete();
                FileWriter fw = new FileWriter(filePath);
                writeToFile(fw, itemslist);
                fw.close();
            }
            catch (IOException e) {
                System.out.println("Something Went Wrong!");
        }



            System.out.print( line + "  Bye. Hope to see you again soon!\n" + line);
            System.exit(0);
    }
}

