public class Deadline extends Todo {
    protected String by; //stores the date/time deadline

    protected DateAndTime myDateTime;

    public Deadline(String description, String deadline) {
        super(description);
        Type = TypeClass.D; //updates Type to D
        by = deadline;
        myDateTime = new DateAndTime(by);
        System.out.println(myDateTime.getDate());
    }

    public String getBy() {
        return myDateTime.getDate();
    }

    public String storeDateAndTime() {
        return "null";
    }
}