module de.die_gfi.maze {
    requires javafx.controls;
    requires javafx.fxml;


    requires kernel;
    requires layout;
    requires io;


    exports de.die_gfi.maze;
    exports de.die_gfi.maze.pdf;
    exports de.die_gfi.maze.util;

    opens de.die_gfi.maze to javafx.fxml;
    opens de.die_gfi.maze.pdf to javafx.fxml;
    opens de.die_gfi.maze.util to javafx.fxml;

}