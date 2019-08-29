public class Deadline extends Todo {
    protected String by; //stores the date/time deadline

    protected DateAndTime myDateTime;

    public Deadline(String description, String deadline) {
        super(description);
        Type = TypeClass.D; //updates Type to D
        by = deadline;
        myDateTime = new DateAndTime(by);
    }

    public String getBy() {
        return by;
    }

    public String getAppointment() {
        return (myDateTime.getDate()+ "," + myDateTime.getTime());
    }
    public String storeDateAndTime() {
        return "null";
    }
}