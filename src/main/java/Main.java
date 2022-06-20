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
    public static final int MAXIMUM_X = 12;
    public static final int MAXIMUM_Y = 12;

    public static final int ROCK = 0;
    public static final int PATH = 1;
    
    


    public static void main(String[] args)
    {
        int[][] maze = generateMaze();
        printMaze( maze );
    }
    
    
    
    public static int[][] generateMaze()
    {
        int[][] maze = initializeMaze();



        return maze;    
    }

    public static int[][] initializeMaze()
    {
        int[][] maze = new int[MAXIMUM_X][MAXIMUM_Y];
        int count = 0;

        for(int y = 0; y < MAXIMUM_Y; y++ )
        {
            for(int x = 0; x < MAXIMUM_X; x++ )
            {
                // maze[x][y] = ROCK;
                maze[x][y] = count ++;
            }
        }

        return maze;
    }

    public static void printMaze( int[][] maze )
    {
        System.out.println( " ---".repeat(MAXIMUM_X) );

        for( int y = 0; y < MAXIMUM_Y; y++ )
        {
            System.out.print( "|" );

            for( int x = 0; x < MAXIMUM_X; x++ )
            {
                System.out.printf( "%5d", maze[x][y] );
//                if( maze[x][y] == ROCK )
//                {
//                    System.out.print( " X |" );
//                }
//                else
//                {
//                    System.out.print( "   |" );
//                }
            }
            System.out.println();
            System.out.println( " ---".repeat(MAXIMUM_X) );
        }
    }
    
    
}