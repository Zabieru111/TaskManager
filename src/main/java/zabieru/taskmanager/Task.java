package zabieru.taskmanager;

public class Task {
    private String name;
    private String date;
    private int priority;
    private boolean completed = false;

    public Task(String name, String date, int priority) {
        this.name = name;
        this.date = date;
        this.priority = priority;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getPriority() {
        return priority;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
