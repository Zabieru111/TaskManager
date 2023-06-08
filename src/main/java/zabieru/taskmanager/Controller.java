package zabieru.taskmanager;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.util.List;

public class Controller {
    private TaskManager taskManager;

    public Controller() {
        taskManager = new TaskManager();
    }

    public List addTask(String name, String date, int importance){
        Task task = new Task(name,date,importance);
        return taskManager.addTask(task);
    }
}