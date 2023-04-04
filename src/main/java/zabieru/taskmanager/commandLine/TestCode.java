package zabieru.taskmanager.commandLine;

import zabieru.taskmanager.Task;
import zabieru.taskmanager.TaskManager;

import java.util.Scanner;

public class TestCode {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        boolean exit = false;
        Scanner user = new Scanner(System.in);
        while(!exit){
            System.out.println("Select Option:");
            System.out.println("1: Add a task");
            System.out.println("2: Remove a task");
            System.out.println("3: List all the current task");
            System.out.println("4: Exit program");
            int choice = user.nextInt();
            switch (choice){
                case 1 -> {
                    Scanner taskInfo = new Scanner(System.in);
                    System.out.println("Please enter the name of your task");
                    String name = taskInfo.nextLine();
                    System.out.println("Please enter a date");
                    String date = taskInfo.nextLine();
                    System.out.println("Please enter the priority");
                    int priority = taskInfo.nextInt();
                    Task task = new Task(name,date,priority);
                    manager.addTask(task);
                }
                case 2 -> {
                    Scanner taskInfo = new Scanner(System.in);
                    String name = taskInfo.nextLine();
                    Task temp = manager.FindTask(name);
                    if (temp==null){
                        System.out.println("task not found");
                        continue;
                    }
                    System.out.println("Task "+temp.getName()+" has been successfully removed");
                    manager.removeTask(temp);
                }
                case 3 -> {
                    manager.ListTask();
                }
                case 4 -> {
                    exit = true;
                }
            }
        }
    }
}
