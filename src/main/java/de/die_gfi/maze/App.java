package de.die_gfi.maze;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application
{
    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        Label l = new Label("something");
        VBox vbox = new VBox(l);
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
