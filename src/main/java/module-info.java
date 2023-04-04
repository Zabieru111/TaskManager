module zabieru.taskmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens zabieru.taskmanager to javafx.fxml;
    exports zabieru.taskmanager;
    exports zabieru.taskmanager.commandLine;
    opens zabieru.taskmanager.commandLine to javafx.fxml;
}