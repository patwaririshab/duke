import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.Vector;

public class Parser {

    private String indes;
    private static String line = "________________________________________________________\n";
    private static Scanner input;



    private static String getCommandWord(String indes) throws InvalidInputException { // ! UPDATE whenever you add new command word
        String[] words = indes.split("\\s",0); // splits the string based on whitespace
        if (words[0].equals("todo") || words[0].equals("done") ||words[0].equals("list") ||words[0].equals("event") || words[0].equals("deadline") || words[0].equals("find") || words[0].equals("delete"))
            return words[0];
        else
            throw new InvalidInputException(words[0]);
    }
    private static String getDescription(String[] words, String type) {
        switch(type){
            case ("done"):
                return words[1];

            case ("todo"):
            case ("find"):
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


    public void processInput(TaskList tasks) {
        while (!indes.equals("bye")) {
            String[] words = indes.split("\\s", 0); //splitting input based on whitespaces
            try {
                switch (getCommandWord(indes)) { //remember to update the list of acceptable words in the method if adding new commands
                    case ("list"):
                        System.out.print(line + "  Here are the tasks in your list:\n");
                        tasks.viewList();
                        break;
                    case ("done"):
                        tasks.updateDone(getDescription(words, "done"));
                        break;
                    case ("todo"):
                        tasks.addToDo( getDescription(words, "todo"), words);
                        break;
                    case ("deadline"):
                        tasks.addSpecial( getDescription(words, "deadline"), words, "by");
                        break;
                    case ("event"):
                        tasks.addSpecial(getDescription(words, "event"), words, "at");
                        break;
                    case ("delete"):
                        tasks.deleteTask(getDescription(words, "done"));
                        break;
                    case ("find"):
                        find.searchList(tasks.getTaskList(), getDescription(words, "find"));
                        break;
                }
            } catch (InvalidInputException e) {
                System.out.println(line + "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n" + line );
            }
            indes = input.nextLine();
        }
        System.out.print( line + "  Bye. Hope to see you again soon!\n" + line);
        System.exit(0);
    }


    public Parser() {
        this.input = new Scanner(System.in); // Simplify call to read input
        this.indes = input.nextLine(); // Reading the whole input description
    }

}



