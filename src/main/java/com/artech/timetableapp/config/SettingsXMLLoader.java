package com.artech.timetableapp.config;

import com.artech.timetableapp.core.ISettings;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class SettingsXMLLoader {
    private final File file;

    public SettingsXMLLoader(File file) {

        this.file = file;
    }

    public ISettings Load() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(this.file);

            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();

            return new Settings(
                    Integer.parseInt(root.getElementsByTagName("lessonsPerDay").item(0).getTextContent()),
                    Integer.parseInt(root.getElementsByTagName("lessonsPerWeek").item(0).getTextContent()),
                    Integer.parseInt(root.getElementsByTagName("windowHeight").item(0).getTextContent()),
                    Integer.parseInt(root.getElementsByTagName("windowWidth").item(0).getTextContent())
            );
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        return new Settings(6, 36, 720, 1280);
    }
}
