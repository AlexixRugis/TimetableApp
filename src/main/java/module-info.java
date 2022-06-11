module com.artech.timetableapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires com.github.miachm.sods;
    requires itextpdf;

    opens com.artech.timetableapp to javafx.fxml;
    opens com.artech.timetableapp.ui.Controllers;
    exports com.artech.timetableapp;
    exports com.artech.timetableapp.core;
    exports com.artech.timetableapp.core.storage;
    opens com.artech.timetableapp.ui.importing;
    opens com.artech.timetableapp.ui.teachers;
    opens com.artech.timetableapp.ui.specialities;
    opens com.artech.timetableapp.ui.groups;
    opens com.artech.timetableapp.ui.subjects;
    opens com.artech.timetableapp.ui.teaching_loads;
    opens com.artech.timetableapp.ui.timetable;
    opens com.artech.timetableapp.ui.Views;
    exports com.artech.timetableapp.config;
    opens com.artech.timetableapp.config to javafx.fxml;
}