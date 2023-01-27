package duke;

import java.util.ArrayList;

/**
 * Represents a collection of Tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList instance.
     */
    TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Adds a Task to this TaskList.
     *
     * @param task Task to be added.
     * @return true (as specified by ArrayList.add).
     */
    public boolean add(Task task) {
        return tasks.add(task);
    }

    /**
     * Removes the Task at specified index.
     *
     * @param idx Index of the Task to be removed.
     * @return The Task that was removed from TaskList.
     */
    public Task remove(int idx) {
        if (idx < 0 || idx > tasks.size()) {
            // out of bounds
            return null;
        }

        return tasks.remove(idx);
    }

    /**
     * Removes all Task from the TaskList.
     */
    public void removeAllTask() {
        tasks.clear();
    }

    /**
     * Consolidates all the String representation of every Task in TaskList.
     * Each representation is separated by a newline.
     * Representation is specified by <code>Storable</code>.
     *
     * @return String representation of all the Task in this TaskList.
     */
    public String prepareFileSave() {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(t.toCsv()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a list of Task in this TaskList which Task description contains the specified search words.
     *
     * @param searchStr String of words to search for in the Task description.
     * @return List of matching Tasks.
     */
    public ArrayList<Task> search(String searchStr) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task t : tasks) {
            if (t.description.contains(searchStr)) {
                results.add(t);
            }
        }
        return results;
    }

    /**
     * Returns the Task from this TaskList at the specified index.
     *
     * @param idx Index of the Task.
     * @return Task at the specified position.
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * Return the number of Task in this TaskList.
     *
     * @return The number of Task in this TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns true if this TaskList contains no Task.
     *
     * @return true if this TaskList contains no Task.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

}
