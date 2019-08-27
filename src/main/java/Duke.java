import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________");
        System.out.println("  Hello I'm Duke!\n  What can I do for you?");
        System.out.println("____________________________________________");
        Scanner input = new Scanner(System.in);
        String word = input.next();
        while( !word.equals("bye")) {
            System.out.println("  " + word);
            System.out.println("____________________________________________");
            word = input.next();
        }
        System.out.println("  " + word);
        System.exit(0);
    }
}
