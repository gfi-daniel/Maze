/*
 * 1. Erstellen Sie ein zweidimensionales Array, in dem die Gänge gegraben werden
 * 2. Füllen Sie das Array mit einem Symbol, welches einen Felsen darstellt
 * 3. Legen Sie für den Bagger Koordinaten x und y an, der in der Mitte startet
 * 4. Graben Sie einen horizontalen Gang, der z.B. 4 Schritte nach rechts gräbt
 * 5. Drucken Sie das Ergebnis auf der Konsole aus
 *
 *
 *
 */


public class Main
{
    public static final int MAXIMUM_X = 17;
    public static final int MAXIMUM_Y = 13;

    public static final int ROCK = 0;
    public static final int PATH = 1;

    public static final int UP = 8;
    public static final int RIGHT = 6;
    public static final int DOWN = 2;
    public static final int LEFT = 4;

    public static char marker = 'a';


    public static int posX = MAXIMUM_X/2;
    public static int posY = MAXIMUM_Y/2;

    public static void main(String[] args)
    {
        int[][] maze = generateMaze();
        printMaze( maze );
    }
    
    
    
    public static int[][] generateMaze()
    {
        int[][] maze = initializeMaze();

        int direction = randomDirection();
        System.out.println( direction );


        drillPath(maze, LEFT);
        drillPath(maze, UP);
        drillPath(maze, UP);

        drillPath(maze, RIGHT);
        drillPath(maze, DOWN);
        drillPath(maze, DOWN);

        return maze;    
    }

    /**
     * Erzeugt ein zweidimensionales int-Array mit vordefinierten Werten für Felsen und gibt dieses zurück
     *
     * @return zweidimensionales int-Array ohne Gänge
     */
    public static int[][] initializeMaze()
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

        return maze;
    }

    /**
     * Druckt das angegebene Labyrinth in der Konsole aus
     *
     * @param maze Das Labyrinth welches ausgedruckt werden soll
     */
    public static void printMaze( int[][] maze )
    {
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
     * Versucht von der aktuellen Stelle im Labyrinth einen zwei Kästchen langen weg, in die angegebene Richtung zu graben.
     *
     * @param maze Das Labyrinth in dem gegraben werden soll
     * @param direction Die Richtung in die versucht wird zu graben
     * @return true wenn erfolgreich in die angegebene Richtung gegraben werden konnte, andernfalls false
     */
    public static boolean drillPath(int[][] maze, int direction)
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
            maze[posX][posY] = marker;
            marker++;
            posX += xv;
            posY += yv;
        }

        return true;

    }



    /**
     * Gibt eine zufällige Zahl zurück, die eine Richtung darstellt
     *
     * @return Zahl die eine Richtung darstellt
     */
    public static int randomDirection()
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