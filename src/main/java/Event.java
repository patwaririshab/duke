public class Event extends Todo {

    protected String at;
    protected DateAndTime myDateTime;

    public Event(String description, String eventat) {
        super(description);
        Type = TypeClass.E; //updates Type to E
        at = eventat;
        myDateTime = new DateAndTime(at);
    }

    public String getAt() {
        return at;
    }

    public String storeDateAndTime() {
        return "null";
    }
}
