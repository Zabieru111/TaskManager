module zabieru.taskmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens zabieru.taskmanager to javafx.fxml;
    exports zabieru.taskmanager;
}