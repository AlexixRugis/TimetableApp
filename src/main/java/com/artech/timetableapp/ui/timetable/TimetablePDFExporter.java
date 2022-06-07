package com.artech.timetableapp.ui.timetable;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.ui.exporting.BaseExporter;
import com.artech.timetableapp.core.model.Day;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.TimetableLessonModel;
import com.artech.timetableapp.core.storage.IStorage;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;

public class TimetablePDFExporter extends BaseExporter<GroupModel> {


    public TimetablePDFExporter(IStorage storage, GroupModel model) {
        super(storage, model);
    }

    @Override
    protected void export(File file) {
        createPdf(file);
    }

    @Override
    protected String getFileName() {
        return String.format("%s.pdf", fixName(this.model.toString()));
    }

    private static final Font headerFont = FontFactory.getFont("fonts\\arial.ttf", "cp1251", BaseFont.EMBEDDED, 16);
    private static final Font textFont = FontFactory.getFont("fonts\\arial.ttf", "cp1251", BaseFont.EMBEDDED, 8);

    public void createPdf(File file) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addContent(Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        paragraph.add(new Paragraph(this.model.name(), headerFont));
        paragraph.add(new Paragraph(this.model.speciality().toString(), textFont));
        paragraph.add(new Paragraph(String.format("%d семестр", this.model.semester()), textFont));

        addEmptyLine(paragraph, 5);

        createTable(paragraph);

        document.add(paragraph);

    }

    private void createTable(Paragraph paragraph) {
        PdfPTable table = new PdfPTable(Day.values().length - 1);

        Collection<TimetableLessonModel> lessons = this.storage.timetableLessonManager().getData(this.model);

        for (Day day : Day.values()) {
            if (day.getIndex() < 1) continue;
            PdfPCell c1 = new PdfPCell(new Phrase(day.toString(), textFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }
        table.setHeaderRows(1);

        Integer lessonsPerDay = TimetableApplication.getInstance().getSettings().getLessonsPerDay();
        String[][] nameTable = new String[Day.values().length - 1][TimetableApplication.getInstance().getSettings().getLessonsPerDay()];
        for (TimetableLessonModel model : lessons) {
            nameTable[model.day().getIndex() - 1][model.lessonNumber() - 1] = String.format("%s\n%s", model.load().subject().name(), model.load().teacher());
        }

        for (int i = 0; i < lessonsPerDay; i++) {
            for (int j = 0; j < Day.values().length - 1; j++) {
                String value = nameTable[j][i] != null ? nameTable[j][i] : "-";
                table.addCell(new PdfPCell(new Phrase(value, textFont)));
            }
        }

        paragraph.add(table);

    }

    private void addEmptyLine(Paragraph paragraph, int amount) {
        for (int i = 0; i < amount; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
