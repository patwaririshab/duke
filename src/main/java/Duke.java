import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

     private static void addWord(String word, List<Task> wordList) {
         Task tempTask = new Task(word);
        wordList.add(tempTask);
        System.out.println("____________________________________________");
        System.out.println("  added: "+ word);
        System.out.println("____________________________________________");
    }

    private static void viewList(List<Task> wordList){
        System.out.println("____________________________________________");
        for(int i = 0; i < wordList.size(); ++i){
            System.out.println((i+1) + ". " + wordList.get(i).getDescription());
        }
        System.out.println("____________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo
                + "____________________________________________\n"
                + "  Hello I'm Duke!\n"
                + "  What can I do for you?\n"
                + "____________________________________________\n");


        Scanner input = new Scanner(System.in);
        // Creating an ArrayList of String
        List<Task> itemslist = new ArrayList<>();

        String inword = input.nextLine();
        while (!inword.equals("bye")) {
            if (inword.equals("list")) {
                viewList(itemslist);
            }
            else {
                addWord(inword, itemslist);
            }
            inword = input.nextLine();
        }
            System.out.println("____________________________________________\n"
                                + "  Bye. Hope to see you again soon!\n"
                                + "____________________________________________\n");
            System.exit(0);
    }
}

