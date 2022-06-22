package de.die_gfi.maze;


import java.util.ArrayList;

public class MazeGenerator
{
    private static final int MAXIMUM_X = (17*2)|1;
    private static final int MAXIMUM_Y = (13*2)|1;

    public static final int ROCK = 0;
    public static final int PATH = 1;

    private static final int UP = 8;
    private static final int RIGHT = 6;
    private static final int DOWN = 2;
    private static final int LEFT = 4;

    private static char marker = 'a';

    private static int posX;
    private static int posY;

    private static int entrancePosX;
    private static int entrancePosY;


    private static ArrayList<Coordinate> coordinates;

    public MazeGenerator()
    {

    }

    /**
     * Generiert ein automatisch ein zufälliges Labyrinth und gibt dieses als ein zweidimensionales int-Array zurück.
     *
     * @return Ein automatisch generiertes Labyrinth
     */
    public int[][] generateMaze()
    {
        int[][] maze = initializeMaze();
        coordinates = new ArrayList<>();
        int pathLength = 0;


        for(int i = 0; i < 40 * 3 * 20; i++)
        {
            if(attemptTwoSteps(maze) && pathLength <= 6)
            {
                pathLength += 2;
            }
            else
            {
                if(coordinates.size() <= 0)
                {
                    break;
                }

                Coordinate coordinate = coordinates.get(0);
                coordinates.remove(0);

                posX = coordinate.getX();
                posY = coordinate.getY();

                pathLength = 0;
            }
        }

        fixBorder(maze);

        return maze;
    }


    /**
     * Setzt die äußeren vier Seitenränder des Labyrinths auf den Block des angegebenen Typs (z. B. ROCK oder PATH)
     *
     * @param maze Das Labyrinth in dem die Seitenränder geändert werden sollen
     * @param type
     */
    private void setBorder( int[][] maze, int type )
    {
        for( int x = 0; x < MAXIMUM_X; x++ )
        {
            maze[x][0] = type;
            maze[x][MAXIMUM_Y-1] = type;
        }

        for( int y = 0; y < MAXIMUM_Y; y++ )
        {
            maze[0][y] = type;
            maze[MAXIMUM_X-1][y] = type;
        }
    }


    /**
     * Ersetzt, außer am Eingang, alle äußeren Pfadblöcke des Labyrinths durch Felsenblöcke.
     *
     * @param maze
     */
    private void fixBorder(int[][] maze)
    {
        setBorder(maze, ROCK);

        maze[entrancePosX][entrancePosY] = PATH;
    }


    /**
     * Versucht den Pfad um zwei Kästchen in eine zufällige Richtung zu erweitern.
     *
     * @param maze Das Labyrinth welches erweitert werden soll
     * @return true wenn der Vorgang erfolgreich war, ansonsten false
     */
    private boolean attemptTwoSteps(int[][] maze)
    {
        int maxTries = 10;
        while( maxTries-- > 0 )
        {
            int direction = randomDirection();

            if(drillTwoSteps(maze, direction))
            {
                Coordinate newCoordinate = new Coordinate( posX, posY );
                coordinates.add(newCoordinate);

                System.out.print(direction + " ");
                return true;
            }
            else
            {
                System.out.print( "(" + direction + ") " );
            }
        }

        System.out.println( "Abbruch" );
        return false;
    }


    /**
     * Gibt ein neues zweidimensionales int-Array mit vordefinierten Werten für Felsen und Pfade zurück.
     *
     * @return zweidimensionales int-Array ohne Gänge
     */
    private int[][] initializeMaze()
    {
        int[][] maze = new int[MAXIMUM_X][MAXIMUM_Y];
        int count = 0;


        for(int y = 0; y < MAXIMUM_Y; y++ )
        {
            for(int x = 0; x < MAXIMUM_X; x++ )
            {
                maze[x][y] = ROCK;
            }
        }

        setBorder( maze, PATH );


        posX = -1;
        posY = 5;

        entrancePosX = posX + 1;
        entrancePosY = posY;
//        maze[posX][posY] = PATH;

        return maze;
    }


    /**
     * Druckt das angegebene Labyrinth in der Konsole aus
     *
     * @param maze Das Labyrinth welches ausgedruckt werden soll
     */
    public void printMaze( int[][] maze )
    {
        System.out.println();
        System.out.println( " ---".repeat(MAXIMUM_X) );

        for( int y = 0; y < MAXIMUM_Y; y++ )
        {
            System.out.print( "|" );

            for( int x = 0; x < MAXIMUM_X; x++ )
            {
                if( maze[x][y] == ROCK )
                {
//                    System.out.print( " X |" );
                    System.out.print( "   |" );
                }
                else
                {
//                    System.out.print( "   |" );
                    System.out.printf( " %c |", (char)(maze[x][y]) );
                }
            }
            System.out.println();
            System.out.println( " ---".repeat(MAXIMUM_X) );
        }
    }


    /**
     * Versucht von der aktuellen Stelle im Labyrinth einen zwei Kästchen langen weg, in die angegebene Richtung zu
     * graben.
     *
     * @param maze Das Labyrinth in dem gegraben werden soll
     * @param direction Die Richtung in die versucht wird zu graben
     * @return true wenn erfolgreich in die angegebene Richtung gegraben werden konnte, andernfalls false
     */
    private boolean drillTwoSteps(int[][] maze, int direction)
    {
        int xv = 0;
        int yv = 0;

        switch(direction) {
            case UP:
            {
                yv = -1;
                break;
            }

            case DOWN:
            {
                yv = 1;
                break;
            }

            case LEFT:
            {
                xv = -1;
                break;
            }

            case RIGHT:
            {
                xv = 1;
                break;
            }

            default:
            {
                throw new RuntimeException("Das hätte nicht passieren dürfen");
            }
        }

        if(!drillAble( maze, xv, yv ))
        {
            return false;
        }

        for(int i = 0; i < 2; i++)
        {
            posX += xv;
            posY += yv;
            maze[posX][posY] = marker;

            marker++;
            if( marker > 'z' )
            {
                marker = 'a';
            }


        }

        return true;

    }


    /**
     * Überprüft, ob in einem Labyrinth um zwei Kästchen in die angegebene Richtung gebohrt werden kann, ohne auf einen
     * Pfad zu treffen.
     *
     * @param maze Das Labyrinth für das die Überprüfung durchgeführt werden soll
     * @param xv x-Wert des Richtungsvektors
     * @param yv y-Wert des Richtungsvektors
     * @return true wenn gebohrt werden kann, ansonsten false
     */
    private boolean drillAble(int[][] maze, int xv, int yv)
    {
        int testX = posX + 2*xv;
        int testY = posY + 2*yv;

        if( testX < 0 || testX >= MAXIMUM_X )
        {
            return false;
        }

        if( testY < 0 || testY >= MAXIMUM_Y )
        {
            return false;
        }

        return (maze[testX][testY] == ROCK);
    }


    /**
     * Gibt eine zufällige Zahl zurück, die eine Richtung darstellt
     *
     * @return Zahl die eine Richtung darstellt
     */
    private int randomDirection()
    {
        return  switch((int)(Math.random()*4))
                {
                    case 0 -> UP;
                    case 1 -> RIGHT;
                    case 2 -> DOWN;
                    case 3 -> LEFT;
                    default -> -1;
                };
    }



}