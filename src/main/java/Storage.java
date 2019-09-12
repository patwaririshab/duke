import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private ArrayList<Todo> itemslist;
    private String filePath;
    private void loadFile(String filePath, ArrayList<Todo> itemslist) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for(String w:lines) {
                String[] splitline = w.split(" " + "\\|" + " ", 0);
                switch (splitline[0]) {
                    case("T"):
                        Todo temp = new Todo(splitline[2]);
                        if (splitline[1].equals("1")) temp.setDone();
                        itemslist.add(temp);
                        break;
                    case("D"):
                        Deadline temp2 = new Deadline(splitline[2],splitline[3]);
                        if (splitline[1].equals("1")) temp2.setDone();
                        itemslist.add(temp2);
                        break;
                    case("E"):
                        Event temp3 = new Event(splitline[2],splitline[3]);
                        if (splitline[1].equals("1")) temp3.setDone();
                        itemslist.add(temp3);
                        break;
                    default:
                        break;
                }
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filePath + "'");
        }
        catch (IOException ex) {
            System.out.println("Error reading file '" + filePath + "'");
        }
    }
    public ArrayList<Todo> load() {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            loadFile(filePath, itemslist); // Load existing list from persistent storage and update itemslist
        }
        else {
            try {
                FileWriter fw = new FileWriter(filePath);
                fw.close();
            } catch (IOException e) {
                System.out.println("Your storage location cannot be accessed.\n");
            }
        }
        return itemslist;
    }
    private void writeToFile(FileWriter fw, ArrayList<Todo> itemslist) throws IOException {
        for(Todo item:itemslist){
            switch(item.getType()){
                case T:
                    fw.write("T | " + (item.getDone() ? "1" : "0") + " | " + item.getDescription() +'\n');
                    break;
                case D:
                    fw.write("D | " + (item.getDone() ? "1" : "0") + " | " + item.getDescription() + " | " + ((Deadline) item).getBy() + "\n");
                    break;
                case E:
                    fw.write("E | " + (item.getDone() ? "1" : "0") + " | " + item.getDescription() + " | " + ((Event) item).getAt() + "\n");
                    break;
            }
        }
    }
    public void updateStorage(TaskList taskList) { // Updating persistent storage
        try {
            File fold = new File(filePath);
            fold.delete();
            FileWriter fw = new FileWriter(filePath);
            writeToFile(fw, taskList.getTaskList());
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Something Went Wrong!");
        }
    }

    public Storage(String filePath) {
        itemslist = new ArrayList<>(); // Creating an arraylist of todo class
        this.filePath = filePath;
    }
}
