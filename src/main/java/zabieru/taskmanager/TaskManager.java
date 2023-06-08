package zabieru.taskmanager;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> TaskList = new ArrayList<>();

    public TaskManager() {
    }

    public List addTask(Task task) {
        if(FindTask(task.getName())==null) {
            TaskList.add(task);
        }
        return TaskList;
    }

    public void removeTask(Task task) {
        TaskList.remove(task);
    }

    public void showTask(Task task) {
        System.out.println("Title:" + task.getName() + ", Date:" + task.getDate() + ", priority:" + task.getPriority());
    }

    public void ListTask() {
        for (int i = 0; i < TaskList.size(); i++) {
            showTask(TaskList.get(i));
        }
    }

    public Task FindTask(String name) {
        for (int i = 0; i < TaskList.size(); i++) {
            if (TaskList.get(i).getName().equals(name)) {
                return TaskList.get(i);
            }
        }
        return null;
    }
    public List getTaskList(){
        return TaskList;
    }
}
