package de.die_gfi.maze.pdf;


import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;


public class MazePdfPrinter
{
    public static void main(String[] args) throws FileNotFoundException
    {

        printMaze(null);


    }



    public static void printMaze( int[][] maze ) throws FileNotFoundException
    {
       //  String zielDatei = "./src/main/resources/de/die_gfi/maze/generated/maze.pdf";
        String zielDatei = "src/main/resources/maze.pdf";

        PdfWriter pdfWriter = new PdfWriter(zielDatei);

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4 /*.rotate()*/);

        Document document = new Document(pdfDocument);

        Paragraph headLine = generateHeadLine("Labyrinth", 18 );
        document.add(headLine);


//        addMaze(document, maze);

        document.close();

    }


    /**
     * Erzeugt einen Absatz der als Überschrift genutzt werden kann, mit fettgedruckter Schrift und einstellbarer
     * Schriftgröße
     *
     * @param fontSize Die Schriftgröße, die der Absatz haben soll
     * @param text     Der Text den die Überschrift enthalten soll
     * @return Paragraph-Objekt welches als Überschrift dient
     */
    private static Paragraph generateHeadLine(String text, int fontSize) {
        Paragraph headLine = new Paragraph(text);
        headLine.setFontSize(fontSize);
        headLine.setBold();
        return headLine;
    }


}
