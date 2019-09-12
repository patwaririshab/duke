public class Duke {
    public static String line = "________________________________________________________\n";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke (String filepath) {
        ui = new Ui(); //Creates Command Line User Interface which calls Parser
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load()); //Loads existing data from persistent storage
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.run(tasks); //After constructor loads existing data/creates new file, run parser to get input
        storage.updateStorage(tasks);
        System.exit(0);
    }

    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + "/src/main/java/task.txt";
        new Duke(filePath).run(); //Creates Duke Object Instance and executes the method run()
    }

}

