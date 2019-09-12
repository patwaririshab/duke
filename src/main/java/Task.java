public class Task {
    protected String description;
    protected TypeClass Type;
    protected enum TypeClass {
        T, D, E;
    }

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
    public TypeClass getType() {
        return this.Type;
    }

}
