public class Todo extends Task {

    private boolean isDone;
    public Todo(String description) {
        super(description);
        Type = TypeClass.T; //updates Type to T
        isDone = false;
    }

    public String isDone() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public boolean getDone() { return isDone; }
    public void setDone() {
        isDone = !isDone;
    }
}
