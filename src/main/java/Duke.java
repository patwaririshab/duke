import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
     public static String line = "____________________________________________\n";
     private static void addWord(String word, List<Task> wordList) {
         Task tempTask = new Task(word);
        wordList.add(tempTask);
        System.out.print(line);
        System.out.println("  added: "+ word);
        System.out.print(line);
    }

    private static void viewList(List<Task> wordList){
        System.out.println("____________________________________________");
        for(int i = 0; i < wordList.size(); ++i){
            System.out.println((i+1) + ". [" + wordList.get(i).getStatusIcon() + "]  " + wordList.get(i).getDescription());
        }
        System.out.print(line);
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
        List<Task> itemslist = new ArrayList<>(); // Creating an ArrayList of String
        String inword = input.nextLine(); // Reading the whole line

        while (!inword.equals("bye")) {
            String[] words = inword.split("\\s",0); // splits the string based on whitespace
            if (inword.equals("list")) {
                viewList(itemslist);
            }
            else if (words[0].equals("done")){
                int itemNo = Integer.parseInt(words[1]);
                itemslist.get(itemNo - 1).updateState();
                System.out.println(line
                            + "Nice! I've marked this task as done:\n"
                            + "  [\u2713] " + itemslist.get(itemNo - 1).getDescription() + "\n"
                            + line);
            }
            else {
                addWord(inword, itemslist);
            }
            inword = input.nextLine();
        }
            System.out.println( line
                                + "  Bye. Hope to see you again soon!\n"
                                + line);
            System.exit(0);
    }
}

