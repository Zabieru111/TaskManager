package zabieru.taskmanager;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.Icon;

import java.awt.*;
import java.io.IOException;

public class Application extends javafx.application.Application {
    private java.awt.Toolkit Toolkit;
    Controller controller = new Controller();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Task Manager!");
        stage.getIcons().add(new Image("Icon.png"));
        stage.setScene(getTaskListScene(stage));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public Scene getTaskListScene(Stage stage){
        VBox root = new VBox();
        Scene taskListScene = new Scene(root, screenSize.width / 1.5, screenSize.height / 1.5);
        HBox menu = new HBox();

        menu.setMinHeight(taskListScene.getHeight() / 8);
        Text menuTitle = new Text("Task Manager");
        menuTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        menu.setBackground(new Background(new BackgroundFill(Color.web("#25a5be"), null, null)));
        HBox buttonGroup = new HBox();
        Button TaskList =new Button("Task List");
        Button Calender = new Button("Calender");
        Button Settings = new Button("Setting");
        buttonGroup.getChildren().addAll(TaskList,Calender,Settings);
        for (Node button : buttonGroup.getChildren()){
            Button temp = (Button)button;
            temp.setMinSize(100,40);
        }
        buttonGroup.setPadding(new Insets(0,0,0,screenSize.width/4.5));
        buttonGroup.setSpacing(5);
        menu.getChildren().addAll(menuTitle,buttonGroup);
        menu.setAlignment(Pos.CENTER);
        buttonGroup.setAlignment(Pos.BOTTOM_RIGHT);
        menu.setPadding(new Insets(0,40,0,40));

        Separator separator = new Separator();

        HBox body = new HBox();
        VBox taskList = new VBox();
        Button addTask = new Button("Add a task");
        addTask.setOnMouseClicked(event -> {

        });
        addTask.setAlignment(Pos.TOP_RIGHT);
        body.getChildren().addAll(taskList,addTask);

        root.getChildren().addAll(menu, separator,body);
        Settings.setOnMouseClicked(event -> {
            stage.setScene(getSettingScene(stage));
        });



        return taskListScene;
    }
    public Scene getSettingScene(Stage stage){
        VBox root = new VBox();
        Scene settingScene = new Scene(root, screenSize.width / 1.5, screenSize.height / 1.5);
        HBox menu = new HBox();

        menu.setMinHeight(settingScene.getHeight() / 8);
        Text menuTitle = new Text("Task Manager");
        menuTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        menu.setBackground(new Background(new BackgroundFill(Color.web("#25a5be"), null, null)));
        HBox buttonGroup = new HBox();
        Button TaskList =new Button("Task List");
        Button Calender = new Button("Calender");
        Button Settings = new Button("Setting");
        buttonGroup.getChildren().addAll(TaskList,Calender,Settings);
        for (Node button : buttonGroup.getChildren()){
            Button temp = (Button)button;
            temp.setMinSize(100,40);
        }
        buttonGroup.setPadding(new Insets(0,0,0,screenSize.width/4.5));
        buttonGroup.setSpacing(5);
        menu.getChildren().addAll(menuTitle,buttonGroup);
        menu.setAlignment(Pos.CENTER);
        buttonGroup.setAlignment(Pos.BOTTOM_RIGHT);
        menu.setPadding(new Insets(0,40,0,40));
        Separator separator = new Separator();
        root.getChildren().addAll(menu, separator);

        TaskList.setOnMouseClicked(event -> {
            stage.setScene(getTaskListScene(stage));
        });



        return settingScene;
    }
}

