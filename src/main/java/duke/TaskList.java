package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    TaskList() {
        tasks = new ArrayList<Task>();
    }

    public boolean add(Task task) {
        return tasks.add(task);
    }

    public Todo addTodo(String description) {
        Todo t = new Todo(description);
        tasks.add(t);
        return t;
    }

    public Deadline addDeadline(String description, String duedate) {
        Deadline d = new Deadline(description, new Parser().parseDateTime(duedate));
        tasks.add(d);
        return d;
    }

    public Event addEvent(String description, String from, String to) {
        Parser p = new Parser();
        Event e = new Event(description, p.parseDateTime(from), p.parseDateTime(to));
        tasks.add(e);
        return e;
    }

    public Task remove(int idx) {
        return tasks.remove(idx);
    }

    public void removeAllTask() {
        tasks.clear();
    }

    public String prepareFileSave() {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(t.toCsv()).append("\n");
        }
        return sb.toString();
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }
    public int size() {
        return tasks.size();
    }
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

}
