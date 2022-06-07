package com.artech.timetableapp.UI.Views;

import com.artech.timetableapp.UI.Controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

/**
 * Предстваление на основе FXML файла
 */
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

    /**
     * Получает путь к FXML файлу
     * @return Путь к FXML файлу
     */
    protected abstract URL getFXMLResourceURL();

    /**
     * Получает контроллер представления
     * @return Контроллер представления
     */
    protected abstract Controller getController();

}
