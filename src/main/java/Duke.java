public class Duke {
    public static String line = "________________________________________________________\n";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke (String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.run(tasks);
    }

    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + "/src/main/java/task.txt";
        new Duke(filePath).run();
    }

}

