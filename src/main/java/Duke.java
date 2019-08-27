import java.util.Scanner;

public class Duke {

    private static void printReply(String word) {
        System.out.println("  " + word);
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
                + "  Hello I'm Duke!\n  What can I do for you?\n"
                + "____________________________________________\n");
        Scanner input = new Scanner(System.in);
        String word = input.nextLine();
        while( !word.equals("bye")){
            printReply(word);
            word = input.nextLine();
        }
        printReply(word);
        System.exit(0);
    }
}
