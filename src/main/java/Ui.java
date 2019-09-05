import java.util.Scanner;

public class Ui {
    protected static String line = "________________________________________________________\n";
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
        System.out.println("Something went wrong");
    }

    public void run(TaskList task) {
        new Parser().processInput(task);
    }

    public Ui() {
        showWelcome();
    }

}
