import java.util.Scanner;


/**
 * The Ui class handles user interaction. When initialized, it prints out
 * DUKE and a Welcome message. It then reads user input lines continuously
 * and calls the Parser to process the input. When the bye command is obtained
 * it terminates the program.
 */
public class Ui {
    private String line = Duke.line;
    private Scanner input;
    private String indes;

    private void showWelcome() {
        String logo =
                " ____        _\n"
                        + "|  _ \\ _   _| | _____\n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo
                + line
                + "  Hello I'm Duke!\n"
                + "  What can I do for you?\n"
                + line);
    }
    public void showLoadingError() {
        System.out.println("No \"task.txt\" file was found. A new task.txt file has been created.");
    }
    public void run(TaskList taskList) {
        this.input = new Scanner(System.in); // Simplify call to read input
        this.indes = input.nextLine(); // Reading the whole input description
        while(!indes.equals("bye")){
            new Parser().processInput(taskList, indes);
            indes = input.nextLine();
        }
        System.out.print( line + "  Bye. Hope to see you again soon!\n" + line);
    }
    public Ui() {
        showWelcome();
    }
}
