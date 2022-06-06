module com.artech.timetableapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires com.github.miachm.sods;

    opens com.artech.timetableapp to javafx.fxml;
    opens com.artech.timetableapp.UI.Controllers;
    exports com.artech.timetableapp;
    opens com.artech.timetableapp.UI.importing;
    opens com.artech.timetableapp.UI.teachers;
    opens com.artech.timetableapp.UI.specialities;
    opens com.artech.timetableapp.UI.groups;
    opens com.artech.timetableapp.UI.subjects;
    opens com.artech.timetableapp.UI.teaching_loads;
    opens com.artech.timetableapp.UI.timetable;
    opens com.artech.timetableapp.UI.Views;
}