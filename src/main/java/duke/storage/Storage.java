package duke.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.Todo;
import duke.Event;
import duke.Deadline;
import duke.TaskList;
import duke.Parser;
import duke.Ui;

/**
 * Handles read and write of the (storable) taskList to/from file
 */
public class Storage {

    /** Relative path of save file */
    private static final String TASKS_FILE_PATH = "./data/duke/tasks.csv";

    private TaskList tasklist;

    /**
     * Constructs instance of Storage using a reference to a TaskList.
     * Read/write is relative to this taskList.
     *
     * @param taskList Reference to a TaskList
     */
    public Storage(TaskList taskList) {
        this.tasklist = taskList;
    }

    /**
     * Writes the data of TaskList to file.
     */
    public void saveDataToFile() {

        // Prepare data into string format for saving
        String fileDataStr = tasklist.prepareFileSave();

        // Write to file
        writeToFile(TASKS_FILE_PATH, fileDataStr);
    }

    /**
     * Reads save file and put them into the referenced TaskList.
     * Does nothing if save file does not exist.
     */
    public void loadDataFromFile() {

        Parser parser = new Parser();

        Path f = Paths.get(TASKS_FILE_PATH);
        if (!Files.exists(f)) {
            return; // No saved data, do nothing
        }

        // Purge taskList
        tasklist.removeAllTask();

        try {
            BufferedReader br = Files.newBufferedReader(f);
            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                String[] taskInfo = currentLine.split(",");

                if (taskInfo[0].compareTo("T") == 0)
                    tasklist.add(new Todo(
                            Boolean.parseBoolean(taskInfo[1]),
                            taskInfo[2]));
                if (taskInfo[0].compareTo("D") == 0)
                    tasklist.add(new Deadline(
                            Boolean.parseBoolean(taskInfo[1]),
                            taskInfo[2],
                            parser.parseDateTime(taskInfo[3], 'T')));
                if (taskInfo[0].compareTo("E") == 0)
                    tasklist.add(new Event(
                            Boolean.parseBoolean(taskInfo[1]),
                            taskInfo[2],
                            parser.parseDateTime(taskInfo[3], 'T'),
                            parser.parseDateTime(taskInfo[4], 'T')));
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (IndexOutOfBoundsException e) {
            new Ui().warn("Corrupt data. Cannot load from file.");
        }
    }

    /**
     * Writes specified file content to specified file path.
     *
     * @param filePath Where to write to file
     * @param fileContent What to write to file
     */
    private void writeToFile(String filePath, String fileContent) {
        // Write prepared data to file
        try {
            Path f = Paths.get(filePath);
            Files.createDirectories(f.getParent()); // Create directory (if not exist)
            if (!Files.exists(f)) {
                Files.createFile(f); // Create non-existing file
            }
            Files.writeString(f, fileContent); // Write to file
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
