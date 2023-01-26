package duke;

import duke.storage.Storable;

public abstract class Task implements Storable {
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    boolean isDone() {
        return isDone;
    }

    void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toCsv() {
        return (isDone ? "true":"false") + "," + description;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "√" : " ") + "] " + description;
    }
}
