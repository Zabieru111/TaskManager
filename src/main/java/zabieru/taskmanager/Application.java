package zabieru.taskmanager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import org.kordamp.ikonli.javafx.Icon;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Application extends javafx.application.Application {
    private java.awt.Toolkit Toolkit;
    Calendar calendar = new Calendar();
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
        VBox tasks = new VBox();
        VBox creation = new VBox();
        tasks.setMinWidth(taskListScene.getWidth()*3/5);
        tasks.setMinHeight(taskListScene.getHeight());



        creation.setMinHeight(taskListScene.getHeight());
        creation.setMinWidth(taskListScene.getWidth()*2/5);
        creation.setAlignment(Pos.TOP_CENTER);
        Separator separator1 = new Separator();
        separator1.setOrientation(Orientation.VERTICAL);
        Button addTask = new Button("Add a task");
        Text text = new Text("Task Creation Menu");
        text.setFont(Font.font(20));

        HBox nameField = new HBox();
        Text name = new Text("Enter the name of the task: ");
        name.setFont(Font.font(16));
        TextField enterName = new TextField();
        nameField.getChildren().addAll(name,enterName);

        HBox dateField = new HBox();
        ChoiceBox month = new ChoiceBox<String>();
        for (int i=0;i<calendar.getMois().size();i++){
            Month temp = calendar.getMois().get(i);
            month.getItems().add(temp.getName());
        }
        month.setValue(LocalDate.now().getMonth().name());
        ChoiceBox day = new ChoiceBox<Integer>();
        ChoiceBox year = new ChoiceBox<Integer>();
        Slider slider = new Slider();
        VBox sliderField = new VBox();
        Label label = new Label(Integer.toString((int)slider.getValue()));
        slider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            label.setText(Integer.toString((int) Math.ceil(newValue.doubleValue())));
        }));
        slider.setValue(0);
        slider.setMax(5);
        slider.setMaxWidth(200);
        sliderField.getChildren().addAll(label,slider);
        setYear(year,month.getSelectionModel().getSelectedIndex());
        setDay(day,month.getSelectionModel().getSelectedIndex(),year.getSelectionModel().getSelectedIndex());
        dateField.getChildren().addAll(month,day,year);
        month.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setDay(day,month.getSelectionModel().getSelectedIndex(),(Integer)year.getItems().get(year.getSelectionModel().getSelectedIndex()));
                setYear(year,month.getSelectionModel().getSelectedIndex());
            }
        });
        year.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setDay(day,month.getSelectionModel().getSelectedIndex(),(Integer)year.getItems().get(year.getSelectionModel().getSelectedIndex()));
            }
        });
        addTask.setOnMouseClicked(event -> {
            String date =year.getItems().get(year.getSelectionModel().getSelectedIndex())+"-"+calendar.getMonth(month.getSelectionModel().getSelectedIndex()).getNumber()+"-"+
                    day.getItems().get(day.getSelectionModel().getSelectedIndex());
            String taskName = enterName.getText();
            int importance = (int)Math.ceil(slider.getValue());
            List<Task>list = controller.addTask(taskName,date,importance);
            tasks.getChildren().clear();
            for(Task task : list){
                HBox temp = new HBox();
                Text tempName= new Text(task.getName());
                tempName.setFont(Font.font(20));
                Text tempDate= new Text(task.getDate());
                tempDate.setFont(Font.font(20));
                Text tempImp = new Text(Integer.toString(task.getPriority()));
                tempImp.setFont(Font.font(20));
                temp.getChildren().addAll(tempName,tempDate,tempImp);
                temp.setSpacing(20);
                tasks.getChildren().add(temp);
            }
            tasks.setSpacing(20);
        });
        creation.getChildren().addAll(text,nameField,dateField,sliderField,addTask);

        addTask.setAlignment(Pos.TOP_RIGHT);
        body.getChildren().addAll(tasks,separator1,creation);

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
    public void setDay(ChoiceBox choiceBox,int index,int year){
        choiceBox.getItems().clear();
        Month temp = calendar.getMonth(index);
        int days = temp.getDays();
        int i=0;
        if (temp.getName().equals(LocalDate.now().getMonth().name())){
            i = LocalDate.now().getDayOfMonth();
        }
        if(temp.getName().equals("FEBRUARY")&&year%4==0){
            days++;
        }
        if (year>LocalDate.now().getYear()){
            i=0;
        }
        for (int j = i;j<days;j++){
            choiceBox.getItems().add(j+1);
        }
        choiceBox.setValue(choiceBox.getItems().get(0));
    }
    public void setYear(ChoiceBox choiceBox,int index){
        choiceBox.getItems().clear();
        int year = LocalDate.now().getYear();
        if (index< calendar.getIndex(calendar.findMonth(LocalDate.now().getMonth().name()))){
            year++;
        }
            for (int i = year; i < year + 5; i++) {
                choiceBox.getItems().add(i);
            }
        if (!choiceBox.getItems().isEmpty()) {
            choiceBox.setValue(choiceBox.getItems().get(0));
        }
    }
}

