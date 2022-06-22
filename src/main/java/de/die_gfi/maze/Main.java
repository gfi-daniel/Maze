package de.die_gfi.maze;

import de.die_gfi.maze.pdf.MazePdfPrinter;
import java.io.FileNotFoundException;


public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        MazeGenerator mazeGenerator = new MazeGenerator();
        int[][] maze = mazeGenerator.generateMaze();
        mazeGenerator.printMaze(maze);

        MazePdfPrinter mazePdfPrinter = new MazePdfPrinter();
        mazePdfPrinter.printMaze(maze);
    }
}