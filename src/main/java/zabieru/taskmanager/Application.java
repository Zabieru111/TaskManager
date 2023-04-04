package zabieru.taskmanager;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Application extends javafx.application.Application {
    private java.awt.Toolkit Toolkit;

    @Override
    public void start(Stage stage) throws IOException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setTitle("Task Manager!");
        stage.getIcons().add(new Image("Icon.png"));
        VBox root = new VBox();
        Scene scene = new Scene(root,screenSize.width/1.5, screenSize.height/1.5);
        HBox menu = new HBox();

        menu.setMinHeight(scene.getHeight()/5);
        Text menuTitle = new Text("Task Manager");
        menuTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,20));
        menu.setBackground(new Background(new BackgroundFill(Color.web("#25a5be"), null, null)));


        menu.getChildren().add(menuTitle);
        Separator separator = new Separator();
        root.getChildren().addAll(menu,separator);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}