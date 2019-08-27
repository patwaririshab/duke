import java.util.Scanner;
import java.util.Vector;

public class Duke {

    private static void addWord(String word, Vector wordList) {
        wordList.add(word);
        System.out.println("____________________________________________");
        System.out.println("  added: "+ word);
        System.out.println("____________________________________________");
    }

    private static void viewList(Vector wordList){
        System.out.println("____________________________________________");
        for(int i = 0; i < wordList.size(); ++i){
            System.out.println((i+1) + ". " + wordList.get(i));
        }
        System.out.println("____________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo
                + "____________________________________________\n"
                + "  Hello I'm Duke!\n"
                + "  What can I do for you?\n"
                + "____________________________________________\n");

        Vector wordList = new Vector(100);
        Scanner input = new Scanner(System.in);

        String word = input.nextLine();
        while (!word.equals("bye")) {
            if (word.equals("list")) {
                viewList(wordList);
            }
            else {
                addWord(word, wordList);
            }
            word = input.nextLine();
        }
            System.out.println("____________________________________________\n"
                                + "  Bye. Hope to see you again soon!\n"
                                + "____________________________________________\n");
            System.exit(0);
    }
}
