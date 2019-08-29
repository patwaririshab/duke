public class Event extends Todo {

    protected String at;

    public Event(String description, String eventat) {
        super(description);
        Type = TypeClass.E; //updates Type to E
        at = eventat;
    }

    public String getAt() {
        return at;
    }
}
