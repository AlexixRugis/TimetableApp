package com.artech.timetableapp.UI.importing;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.FXMLView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Collection;

public class ImportingActionDialogView extends FXMLView {

    private CheckBox deleteData;
    private Label fileLabel;
    private Button fileButton;
    private File selectedFile;

    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("importing-dialog-view.fxml");
    }

    @Override
    protected Node build() {
        Node build = super.build();

        deleteData = (CheckBox) build.lookup("#delete_data");
        fileLabel = (Label) build.lookup("#file_label");
        fileButton = (Button) build.lookup("#file_button");

        fileButton.setOnAction(event -> selectFile());

        return build;
    }

    @Override
    protected Controller getController() {
        return null;
    }

    @Override
    public String getName() {
        return "Импорт";
    }

    private void selectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ODS files", "*.ods")
        );
        Stage mainStage = TimetableApplication.getInstance().getPrimaryStage();
        File selected = fileChooser.showOpenDialog(mainStage);
        if (selected != null) selectedFile = selected;

        fileLabel.setText(selectedFile != null ? selectedFile.getName() : "Выберите файл");
    }

    public Boolean getDeleteData() {
        return this.deleteData.isSelected();
    }

    public File getSelectedFile() {
        return selectedFile;
    }
}
