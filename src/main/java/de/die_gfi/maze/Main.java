package de.die_gfi.maze;

import de.die_gfi.maze.pdf.MazePdfPrinter;
import java.io.FileNotFoundException;

// KEEP THIS FILE
public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        generateManyMazes(3);
    }

    private static void generateManyMazes(int amount) throws FileNotFoundException
    {
        MazeGenerator mazeGenerator = new MazeGenerator();
        int[][] maze;
        MazePdfPrinter mazePdfPrinter = new MazePdfPrinter();


        for(int i = 1; i <= amount; i++ )
        {
            String fileName = String.format( "maze%08d.pdf", i );
            System.out.print( "Generiere " + fileName + " ..." );

            maze = mazeGenerator.generateMaze();
            mazePdfPrinter.printMaze(maze, fileName);

            System.out.print( "OK\n" );
        }
    }
}