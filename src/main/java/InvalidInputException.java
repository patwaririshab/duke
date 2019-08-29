public class InvalidInputException extends Exception {
    private String command;

    public InvalidInputException(String command) {
        this.command = command;
    }

    public boolean isValid() {
        if (command.equals("todo") || command.equals("done") || command.equals("list") ||command.equals("event")){
            return true;
        }
        return false;
    }
}
