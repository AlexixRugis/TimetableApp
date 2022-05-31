package com.artech.timetableapp.UI.Views;

import com.artech.timetableapp.UI.Controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

public abstract class FXMLView extends View {

    @Override
    protected Node build() {
        FXMLLoader loader = new FXMLLoader(getFXMLResourceURL());
        loader.setController(getController());

        try {
            return new Scene(loader.load(), 256, 256).getRoot();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract URL getFXMLResourceURL();
    protected abstract Controller getController();

}
