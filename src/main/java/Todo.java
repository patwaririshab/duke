public class Todo extends Task {

    private boolean isDone;
    protected TypeClass Type;
    protected enum TypeClass {
        T, D, E;
    }
    public Todo(String description) {
        super(description);
        Type = TypeClass.T; //updates Type to T
        isDone = false;
    }

    public String isDone() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        isDone = true;
    }

    public TypeClass getType() {
        return this.Type;
    }
}
