module com.artech.timetableapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.drombler.fx.core.docking;

    opens com.artech.timetableapp to javafx.fxml;
    opens com.artech.timetableapp.UI.Controllers;
    exports com.artech.timetableapp;
}