public class Deadline extends Todo {
    protected String by; //stores the date/time deadline
    public Deadline(String description, String deadline) {
        super(description);
        Type = TypeClass.D; //updates Type to D
        by = deadline;

    }

    public String getBy() {
        return by;
    }
}