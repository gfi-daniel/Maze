package de.die_gfi.maze.pdf;


import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import de.die_gfi.maze.MazeGenerator;

import java.io.FileNotFoundException;



public class MazePdfPrinter
{
    private final float SIDE_LENGTH = 10;


    public MazePdfPrinter()
    {

    }

    /**
     *
     *
     * @param maze
     * @throws FileNotFoundException
     */
    public void printMaze( int[][] maze ) throws FileNotFoundException
    {
       //  String zielDatei = "./src/main/resources/de/die_gfi/maze/generated/maze.pdf";
        String zielDatei = "src/main/resources/maze.pdf";

        PdfWriter pdfWriter = new PdfWriter(zielDatei);

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4 /*.rotate()*/);

        Document document = new Document(pdfDocument);

        Paragraph headLine = generateHeadLine("Labyrinth", 18 );
        document.add(headLine);


        addMaze(document, maze);

        document.close();
    }


    /**
     * Fügt ein Labyrinth dem PDF-Dokument hinzu
     *
     * @param document Das Dokument in welches das Labyrinth hinzugefügt werden soll
     * @param maze Das
     */
    private void addMaze( Document document, int[][] maze )
    {
        int maximumX = maze.length;
        int maximumY = maze[0].length;

        System.out.printf( "maximumX = %d\n", maximumX );
        System.out.printf( "maximumY = %d\n", maximumY );


        float[] spaltenBreiten = new float[maximumX];

        for( int i = 0; i < maximumX; i++ )
        {
            spaltenBreiten[i] = SIDE_LENGTH;
        }

        Table table = new Table(spaltenBreiten);


        for( int y = 0; y < maximumY; y++ )
        {
            for( int x = 0; x < maximumX; x++ )
            {
                int block = maze[x][y];
                Cell cell = new Cell();

                String text = "" + (char)block;
                Paragraph paragraph = new Paragraph(text);
                cell.add(paragraph);

                if( block == MazeGenerator.ROCK )
                {
                    cell.setBackgroundColor(ColorConstants.BLACK/*LIGHT_GRAY*/);
                }

                table.addCell(cell);
            }

        }


        document.add(table);
    }




    /**
     * Erzeugt einen Absatz der als Überschrift genutzt werden kann, mit fettgedruckter Schrift und einstellbarer
     * Schriftgröße
     *
     * @param fontSize Die Schriftgröße, die der Absatz haben soll
     * @param text     Der Text den die Überschrift enthalten soll
     * @return Paragraph-Objekt welches als Überschrift dient
     */
    private Paragraph generateHeadLine(String text, int fontSize)
    {
        Paragraph headLine = new Paragraph(text);
        headLine.setFontSize(fontSize);
        headLine.setBold();
        return headLine;
    }


}
